package com.example.repository;

import com.example.dto.VideoRecommendationDTO;
import jakarta.inject.Singleton;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class VideoRepository {
    private final Driver driver;

    VideoRepository(Driver driver) {
        this.driver = driver;
    }

    public List<VideoRecommendationDTO> recommendVideos(int userId) {
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> recommendVideos(tx, userId));
        }
    }

    private List<VideoRecommendationDTO> recommendVideos(Transaction tx, int userId) {
        String query = "MATCH (user:User {id: $userId})-[:SUBSCRIBES_TO]->(tag:Tag)<-[:CONTAINS]-(recommendedVideo:Video) " +
                "WHERE NOT (user)-[:LIKES|WATCHES]->(recommendedVideo) " +
                "RETURN recommendedVideo.title AS title, recommendedVideo.views AS views " +
                "ORDER BY views DESC " +
                "LIMIT 10";

        var result = tx.run(query, org.neo4j.driver.Values.parameters("userId", userId));
        return getVideoRecommendationDTOS(result);
    }

    public List<VideoRecommendationDTO> recommendVideosByTag(int userId, String tagName) {
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> recommendVideosByTag(tx, userId, tagName));
        }
    }

    private List<VideoRecommendationDTO> recommendVideosByTag(Transaction tx, int userId, String tagName) {
        String query = "MATCH (user:User {id: $userId})-[:SUBSCRIBES_TO]->(tag:Tag {name: $tagName})<-[:CONTAINS]-(recommendedVideo:Video) " +
                "WHERE NOT (user)-[:LIKES|WATCHES]->(recommendedVideo) " +
                "RETURN recommendedVideo.title AS title, recommendedVideo.views AS views " +
                "ORDER BY views DESC " +
                "LIMIT 10";

        var result = tx.run(query, org.neo4j.driver.Values.parameters("userId", userId, "tagName", tagName));
        return getVideoRecommendationDTOS(result);
    }

    private List<VideoRecommendationDTO> getVideoRecommendationDTOS(Result result) {
        List<VideoRecommendationDTO> recommendedVideos = new ArrayList<>();

        while (result.hasNext()) {
            var record = result.next();
            var title = record.get("title").asString();
            var views = record.get("views").asInt();

            recommendedVideos.add(new VideoRecommendationDTO(title, views));
        }

        return recommendedVideos;
    }
}
