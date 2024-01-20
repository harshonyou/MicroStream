package com.example.repository;

import com.example.dto.RecommendedVideoDTO;
import com.example.model.Tag;
import com.example.model.User;
import com.example.model.Video;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false, environments = "no-streams")
class Neo4jVideoRepositoryTest {
    @Inject
    Neo4jVideoRepository neo4jVideoRepository;

    @Inject
    Neo4jTagRepository neo4jTagRepository;

    @Inject
    Neo4jUserRepository neo4jUserRepository;

    @AfterEach
    public void tearDown() {
        neo4jVideoRepository.deleteAll();
        neo4jTagRepository.deleteAll();
        neo4jUserRepository.deleteAll();
    }

    @Test
    public void testFindByIdWithNonExistingId() {
        assertFalse(neo4jVideoRepository.findById(UUID.fromString("2b6611cb-8627-4b69-be69-9e6424b229fe")).isPresent());
    }

    @Test
    public void testPostVideo() {
        UUID videoId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        String videoName = "video-name";
        Long videoViews = 0L;
        String userId = "user-id";
        String userName = "user-name";
        Set<Tag> tags = Set.of(new Tag("tag-name"));
        neo4jUserRepository.addUser(new User(userId, userName));
        neo4jTagRepository.addTag(tags.iterator().next());
        neo4jVideoRepository.postVideo(userId, new Video(videoId, videoName, videoViews), tags);

        Optional<Video> video = neo4jVideoRepository.findById(videoId);
        assertTrue(video.isPresent());
        assertEquals(videoId, video.get().getId());
        assertEquals(videoName, video.get().getTitle());
        assertEquals(videoViews, video.get().getViews());
    }

    @Test
    public void testLikeVideo() {
        UUID videoId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        String videoName = "video-name";
        Long videoViews = 0L;
        String userId = "user-id";
        String userName = "user-name";
        Set<Tag> tags = Set.of(new Tag("tag-name"));
        neo4jUserRepository.addUser(new User(userId, userName));
        neo4jTagRepository.addTag(tags.iterator().next());
        neo4jVideoRepository.postVideo(userId, new Video(videoId, videoName, videoViews), tags);
        neo4jVideoRepository.likeVideo(videoId, userId);

        Optional<Video> video = neo4jVideoRepository.findById(videoId);
        assertTrue(video.isPresent());
        assertEquals(videoId, video.get().getId());
        assertEquals(videoName, video.get().getTitle());
        assertEquals(videoViews, video.get().getViews());

        boolean isLiked = neo4jVideoRepository.isLiked(videoId, userId);
        assertTrue(isLiked);
    }

    @Test
    public void testDislikeVideo() {
        UUID videoId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        String videoName = "video-name";
        Long videoViews = 0L;
        String userId = "user-id";
        String userName = "user-name";
        Set<Tag> tags = Set.of(new Tag("tag-name"));
        neo4jUserRepository.addUser(new User(userId, userName));
        neo4jTagRepository.addTag(tags.iterator().next());
        neo4jVideoRepository.postVideo(userId, new Video(videoId, videoName, videoViews), tags);
        neo4jVideoRepository.likeVideo(videoId, userId);
        neo4jVideoRepository.dislikeVideo(videoId, userId);

        Optional<Video> video = neo4jVideoRepository.findById(videoId);
        assertTrue(video.isPresent());
        assertEquals(videoId, video.get().getId());
        assertEquals(videoName, video.get().getTitle());
        assertEquals(videoViews, video.get().getViews());

        boolean isLiked = neo4jVideoRepository.isLiked(videoId, userId);
        assertFalse(isLiked);
    }

    @Test
    public void testWatchVideo() {
        UUID videoId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        String videoName = "video-name";
        Long videoViews = 0L;
        String userId = "user-id";
        String userName = "user-name";
        Set<Tag> tags = Set.of(new Tag("tag-name"));
        neo4jUserRepository.addUser(new User(userId, userName));
        neo4jTagRepository.addTag(tags.iterator().next());
        neo4jVideoRepository.postVideo(userId, new Video(videoId, videoName, videoViews), tags);
        neo4jVideoRepository.watchVideo(videoId, userId);
        neo4jVideoRepository.incrementVideoViews(videoId);

        Optional<Video> video = neo4jVideoRepository.findById(videoId);
        assertTrue(video.isPresent());
        assertEquals(videoId, video.get().getId());
        assertEquals(videoName, video.get().getTitle());
        assertEquals(videoViews + 1, video.get().getViews());
    }

    @Test
    public void testGetUserRecommendationsByTag() {
        UUID videoId1 = UUID.fromString("00000000-0000-0000-0000-000000000000");
        String videoName1 = "video-name-1";
        Long videoViews1 = 0L;
        UUID videoId2 = UUID.fromString("00000000-0000-0000-0000-000000000001");
        String videoName2 = "video-name-2";
        Long videoViews2 = 0L;
        UUID videoId3 = UUID.fromString("00000000-0000-0000-0000-000000000002");
        String videoName3 = "video-name-3";
        Long videoViews3 = 0L;
        String userId1 = "user-id-1";
        String userId2 = "user-id-2";
        String userName1 = "user-name-1";
        String userName2 = "user-name-2";
        String tagName1 = "tag-name-1";
        String tagName2 = "tag-name-2";
        Set<Tag> tags1 = Set.of(new Tag(tagName1));
        Set<Tag> tags2 = Set.of(new Tag(tagName2));
        neo4jUserRepository.addUser(new User(userId1, userName1));
        neo4jUserRepository.addUser(new User(userId2, userName2));
        neo4jTagRepository.addTag(tags1.iterator().next());
        neo4jTagRepository.addTag(tags2.iterator().next());
        neo4jVideoRepository.postVideo(userId1, new Video(videoId1, videoName1, videoViews1), tags1);
        neo4jVideoRepository.postVideo(userId1, new Video(videoId2, videoName2, videoViews2), tags1);
        neo4jVideoRepository.postVideo(userId1, new Video(videoId3, videoName3, videoViews3), tags2);
        neo4jTagRepository.associateUserWithTag(tagName1, userId2);
        neo4jVideoRepository.watchVideo(videoId1, userId2);
        neo4jVideoRepository.incrementVideoViews(videoId1);

        List<RecommendedVideoDTO> recommendedVideos = neo4jVideoRepository.getUserRecommendationsByTag(userId2, tagName1);
        assertEquals(1, recommendedVideos.size());
        assertEquals(videoId2, recommendedVideos.get(0).getId());
    }

    @Test
    public void testGetUserRecommendations() {
        UUID videoId1 = UUID.fromString("00000000-0000-0000-0000-000000000000");
        String videoName1 = "video-name-1";
        Long videoViews1 = 0L;
        UUID videoId2 = UUID.fromString("00000000-0000-0000-0000-000000000001");
        String videoName2 = "video-name-2";
        Long videoViews2 = 0L;
        UUID videoId3 = UUID.fromString("00000000-0000-0000-0000-000000000002");
        String videoName3 = "video-name-3";
        Long videoViews3 = 0L;
        UUID videoId4 = UUID.fromString("00000000-0000-0000-0000-000000000003");
        String videoName4 = "video-name-4";
        Long videoViews4 = 0L;
        String userId1 = "user-id-1";
        String userId2 = "user-id-2";
        String userName1 = "user-name-1";
        String userName2 = "user-name-2";
        String tagName1 = "tag-name-1";
        String tagName2 = "tag-name-2";
        String tagName3 = "tag-name-3";
        Set<Tag> tags1 = Set.of(new Tag(tagName1));
        Set<Tag> tags2 = Set.of(new Tag(tagName2));
        Set<Tag> tags3 = Set.of(new Tag(tagName3));
        neo4jUserRepository.addUser(new User(userId1, userName1));
        neo4jUserRepository.addUser(new User(userId2, userName2));
        neo4jTagRepository.addTag(tags1.iterator().next());
        neo4jTagRepository.addTag(tags2.iterator().next());
        neo4jTagRepository.addTag(tags3.iterator().next());
        neo4jVideoRepository.postVideo(userId1, new Video(videoId1, videoName1, videoViews1), tags1);
        neo4jVideoRepository.postVideo(userId1, new Video(videoId2, videoName2, videoViews2), tags2);
        neo4jVideoRepository.postVideo(userId1, new Video(videoId3, videoName3, videoViews3), tags2);
        neo4jVideoRepository.postVideo(userId1, new Video(videoId4, videoName4, videoViews4), tags3);
        neo4jTagRepository.associateUserWithTag(tagName1, userId2);
        neo4jTagRepository.associateUserWithTag(tagName2, userId2);
        neo4jVideoRepository.watchVideo(videoId3, userId2);
        neo4jVideoRepository.incrementVideoViews(videoId3);

        List<RecommendedVideoDTO> recommendedVideos = neo4jVideoRepository.getUserRecommendations(userId2);
        assertEquals(2, recommendedVideos.size());
        assertEquals(videoId1, recommendedVideos.get(0).getId());
        assertEquals(videoId2, recommendedVideos.get(1).getId());
    }

    @Test
    public void testGetUserTimeline() {
        UUID videoId1 = UUID.fromString("00000000-0000-0000-0000-000000000000");
        String videoName1 = "video-name-1";
        Long videoViews1 = 0L;
        UUID videoId2 = UUID.fromString("00000000-0000-0000-0000-000000000001");
        String videoName2 = "video-name-2";
        Long videoViews2 = 0L;
        UUID videoId3 = UUID.fromString("00000000-0000-0000-0000-000000000002");
        String videoName3 = "video-name-3";
        Long videoViews3 = 0L;
        UUID videoId4 = UUID.fromString("00000000-0000-0000-0000-000000000003");
        String videoName4 = "video-name-4";
        Long videoViews4 = 0L;
        String userId1 = "user-id-1";
        String userId2 = "user-id-2";
        String userName1 = "user-name-1";
        String userName2 = "user-name-2";
        String tagName1 = "tag-name-1";
        String tagName2 = "tag-name-2";
        String tagName3 = "tag-name-3";
        Set<Tag> tags1 = Set.of(new Tag(tagName1));
        Set<Tag> tags2 = Set.of(new Tag(tagName2));
        Set<Tag> tags3 = Set.of(new Tag(tagName3));
        neo4jUserRepository.addUser(new User(userId1, userName1));
        neo4jUserRepository.addUser(new User(userId2, userName2));
        neo4jTagRepository.addTag(tags1.iterator().next());
        neo4jTagRepository.addTag(tags2.iterator().next());
        neo4jTagRepository.addTag(tags3.iterator().next());
        neo4jVideoRepository.postVideo(userId1, new Video(videoId1, videoName1, videoViews1), tags1);
        neo4jVideoRepository.postVideo(userId1, new Video(videoId2, videoName2, videoViews2), tags2);
        neo4jVideoRepository.postVideo(userId1, new Video(videoId3, videoName3, videoViews3), tags2);
        neo4jVideoRepository.postVideo(userId1, new Video(videoId4, videoName4, videoViews4), tags3);
        neo4jTagRepository.associateUserWithTag(tagName1, userId2);
        neo4jVideoRepository.watchVideo(videoId2, userId2);
        neo4jVideoRepository.incrementVideoViews(videoId2);
        neo4jVideoRepository.likeVideo(videoId2, userId2);

        List<RecommendedVideoDTO> timeline = neo4jVideoRepository.getUserTimeline(userId2);
        assertEquals(2, timeline.size());
        assertEquals(videoId1, timeline.get(0).getId());
        assertEquals(videoId3, timeline.get(1).getId());
    }
}