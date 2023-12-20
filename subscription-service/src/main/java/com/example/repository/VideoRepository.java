package com.example.repository;

import com.example.dto.RecommendedVideoDTO;
import com.example.model.Tag;
import com.example.model.Video;
import jakarta.inject.Singleton;
import org.neo4j.driver.*;

import java.util.*;

@Singleton
public class VideoRepository {
    private final Driver driver;

    VideoRepository(Driver driver) {
        this.driver = driver;
    }

    public void postVideo(String userId, Video video, Set<Tag> tags) {
        try (Session session = driver.session()) {
            session.writeTransaction(tx -> postVideo(tx, userId, video, tags));
        }
    }
    public Void postVideo(Transaction tx, String userId, Video video, Set<Tag> tags) {
        String createVideoQuery = "CREATE (v:Video {id: $id, title: $title, views: $views})";
        tx.run(createVideoQuery, org.neo4j.driver.Values.parameters(
                "id", String.valueOf(video.getId()),
                "title", video.getTitle(),
                "views", video.getViews()));

        String relateUserToVideoQuery = "MATCH (u:User {id: $userId}), (v:Video {id: $videoId}) CREATE (u)-[:POSTS]->(v)";
        tx.run(relateUserToVideoQuery, org.neo4j.driver.Values.parameters(
                "userId", userId,
                "videoId", String.valueOf(video.getId())));

        for (Tag tag : tags) {
            String relateVideoToTagQuery = "MATCH (v:Video {id: $videoId}), (t:Tag {name: $tagName}) MERGE (v)-[:CONTAINS]->(t)";
            tx.run(relateVideoToTagQuery, org.neo4j.driver.Values.parameters(
                    "videoId", String.valueOf(video.getId()),
                    "tagName", tag.getName()));
        }
        return null;
    }

    public void likeVideo(UUID videoId, String userId) {
        try (Session session = driver.session()) {
            session.writeTransaction(tx -> likeVideo(tx, videoId, userId));
        }
    }

    private Void likeVideo(Transaction tx, UUID videoId, String userId) {
        String query = "MATCH (u:User {id: $userId}), (v:Video {id: $videoId}) MERGE (u)-[:LIKES]->(v)";

        tx.run(query, org.neo4j.driver.Values.parameters(
                "userId", userId,
                "videoId", String.valueOf(videoId)));
        return null;
    }

    public void incrementVideoViews(UUID videoId) {
        try (Session session = driver.session()) {
            session.writeTransaction(tx -> incrementVideoViews(tx, videoId));
        }
    }

    private Void incrementVideoViews(Transaction tx, UUID videoId) {
        String query = """
                MATCH (v:Video {id: $videoId})
                SET v.views = coalesce(v.views, 0) + 1
                """;

        tx.run(query, org.neo4j.driver.Values.parameters(
                "videoId", videoId));
        return null;
    }

    public void watchVideo(UUID videoId, String userId) {
        try (Session session = driver.session()) {
            session.writeTransaction(tx -> watchVideo(tx, videoId, userId));
        }
    }

    private Void watchVideo(Transaction tx, UUID videoId, String userId) {
        String query = "MATCH (u:User {id: $userId}), (v:Video {id: $videoId}) MERGE (u)-[:WATCHES]->(v)";

        tx.run(query, org.neo4j.driver.Values.parameters(
                "userId", userId,
                "videoId", String.valueOf(videoId)));
        return null;
    }

    public List<RecommendedVideoDTO> getUserTimeline(String userId) {
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> getUserTimeline(tx, userId));
        }
    }

    private List<RecommendedVideoDTO> getUserTimeline(Transaction tx, String userId) {
        String query = """
                MATCH (user:User {id: $userId})-[:SUBSCRIBES_TO]->(subscribedTag:Tag)
                WITH user, COLLECT(subscribedTag) AS subscribedTags
                OPTIONAL MATCH (user)-[:LIKES]->(:Video)-[:CONTAINS]->(likedTag:Tag)
                WITH user, subscribedTags, COLLECT(likedTag) AS likedTags
                WITH user, REDUCE(output = [], t IN (subscribedTags + likedTags) | output + t) AS allTags
                UNWIND allTags AS tag
                MATCH (tag)<-[:CONTAINS]-(recommendedVideo:Video)
                WHERE NOT (user)-[:LIKES|WATCHES]->(recommendedVideo)
                WITH recommendedVideo, recommendedVideo.id AS Id, COLLECT(DISTINCT tag.name) AS Tags, recommendedVideo.views AS Views
                RETURN recommendedVideo.title AS RecommendedVideo, Id, Tags, Views
                ORDER BY SIZE(Tags) DESC, Views DESC
                LIMIT 10;
                """;

        var result = tx.run(query, org.neo4j.driver.Values.parameters("userId", userId));
        return getVideoRecommendationDTOS(result);
    }

    public List<RecommendedVideoDTO> getUserRecommendations(String userId) {
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> getUserRecommendations(tx, userId));
        }
    }

    private List<RecommendedVideoDTO> getUserRecommendations(Transaction tx, String userId) {
        String query = """
                MATCH (user:User {id: $userId})-[:SUBSCRIBES_TO]->(tag:Tag)<-[:CONTAINS]-(recommendedVideo:Video)
                WHERE NOT (user)-[:LIKES|:WATCHES]->(recommendedVideo)
                WITH recommendedVideo, recommendedVideo.id AS Id, COLLECT(tag.name) AS Tags, recommendedVideo.views AS Views
                RETURN recommendedVideo.title AS RecommendedVideo, Id, Tags, Views
                ORDER BY SIZE(Tags) DESC, Views DESC
                LIMIT 10;
                """;

        var result = tx.run(query, org.neo4j.driver.Values.parameters("userId", userId));
        return getVideoRecommendationDTOS(result);
    }

    public List<RecommendedVideoDTO> getUserRecommendationsByTag(String userId, String tagName) {
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> getUserRecommendationsByTag(tx, userId, tagName));
        }
    }

    private List<RecommendedVideoDTO> getUserRecommendationsByTag(Transaction tx, String userId, String tagName) {
        String query = """
                MATCH (user:User {id: $userId})-[:LIKES|WATCHES]->(watchedVideo:Video)
                WITH COLLECT(watchedVideo) AS watchedVideos
                MATCH (tag:Tag {name: $tagName})<-[:CONTAINS]-(recommendedVideo:Video)
                WHERE NONE(watchedVideo IN watchedVideos WHERE watchedVideo = recommendedVideo)
                WITH recommendedVideo, recommendedVideo.id AS Id, COLLECT(tag.name) AS Tags, recommendedVideo.views AS Views
                RETURN recommendedVideo.title AS RecommendedVideo, Id, Tags, Views
                ORDER BY Views DESC
                LIMIT 10;
                """;

        var result = tx.run(query, org.neo4j.driver.Values.parameters("userId", userId, "tagName", tagName));
        return getVideoRecommendationDTOS(result);
    }

    private List<RecommendedVideoDTO> getVideoRecommendationDTOS(Result result) {
        List<RecommendedVideoDTO> recommendedVideos = new ArrayList<>();

        while (result.hasNext()) {
            var record = result.next();

            UUID id = UUID.fromString(record.get("Id").asString());
            String title = record.get("RecommendedVideo").asString();
            Set<String> affinityTags = new HashSet<>(record.get("Tags").asList(Value::asString));
            long views = record.get("Views").asLong();

            recommendedVideos.add(new RecommendedVideoDTO(id, title, affinityTags, views));
        }

        return recommendedVideos;
    }
}
