package com.example.service;

import com.example.dto.RecommendedVideoDTO;
import com.example.dto.TagDTO;
import com.example.dto.VideoDTO;
import com.example.repository.Neo4jTagRepository;
import com.example.repository.Neo4jUserRepository;
import com.example.repository.Neo4jVideoRepository;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false, environments = "no-streams")
class RecommendationServiceImplTest {
    @Inject
    Neo4jVideoRepository videoRepository;

    @Inject
    Neo4jTagRepository tagRepository;

    @Inject
    Neo4jUserRepository userRepository;

    @Inject
    SubscriptionServiceImpl subscriptionService;

    @Inject
    VideoServiceImpl videoService;

    @Inject
    TagServiceImpl tagService;

    @Inject
    UserServiceImpl userService;

    @Inject
    RecommendationServiceImpl recommendationService;

    @AfterEach
    public void tearDown() {
        videoRepository.deleteAll();
        tagRepository.deleteAll();
        userRepository.deleteAll();
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
        Set<TagDTO> tags1 = Set.of(new TagDTO(tagName1));
        Set<TagDTO> tags2 = Set.of(new TagDTO(tagName2));
        userService.addUser(userId1, userName1);
        userService.addUser(userId2, userName2);
        tagService.addTag(tagName1);
        tagService.addTag(tagName2);
        videoService.postVideo(userId1, new VideoDTO(videoId1, videoName1, videoViews1), tags1);
        videoService.postVideo(userId1, new VideoDTO(videoId2, videoName2, videoViews2), tags1);
        videoService.postVideo(userId1, new VideoDTO(videoId3, videoName3, videoViews3), tags2);
        subscriptionService.subscribeUserToTag(userId1, userId2);
        videoService.watchVideo(videoId1, userId2);

        List<RecommendedVideoDTO> recommendations = recommendationService.getUserRecommendations(userId2, tagName1);
        assertEquals(1, recommendations.size());
        assertEquals(videoId2, recommendations.get(0).getId());
    }

    @Test
    public void testGetUserRecommendation() {
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
        Set<TagDTO> tags1 = Set.of(new TagDTO(tagName1));
        Set<TagDTO> tags2 = Set.of(new TagDTO(tagName2));
        Set<TagDTO> tags3 = Set.of(new TagDTO(tagName3));

        userService.addUser(userId1, userName1);
        userService.addUser(userId2, userName2);
        tagService.addTag(tagName1);
        tagService.addTag(tagName2);
        tagService.addTag(tagName3);
        videoService.postVideo(userId1, new VideoDTO(videoId1, videoName1, videoViews1), tags1);
        videoService.postVideo(userId1, new VideoDTO(videoId2, videoName2, videoViews2), tags2);
        videoService.postVideo(userId1, new VideoDTO(videoId3, videoName3, videoViews3), tags2);
        videoService.postVideo(userId1, new VideoDTO(videoId4, videoName4, videoViews4), tags3);
        subscriptionService.subscribeUserToTag(tagName1, userId2);
        subscriptionService.subscribeUserToTag(tagName2, userId2);
        videoService.watchVideo(videoId3, userId2);

        List<RecommendedVideoDTO> recommendedVideos = recommendationService.getUserRecommendations(userId2);
        assertEquals(2, recommendedVideos.size());

        assertTrue(recommendedVideos.stream().anyMatch(video -> video.getId().equals(videoId1)));
        assertTrue(recommendedVideos.stream().anyMatch(video -> video.getId().equals(videoId2)));
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
        Set<TagDTO> tags1 = Set.of(new TagDTO(tagName1));
        Set<TagDTO> tags2 = Set.of(new TagDTO(tagName2));
        Set<TagDTO> tags3 = Set.of(new TagDTO(tagName3));

        userService.addUser(userId1, userName1);
        userService.addUser(userId2, userName2);
        tagService.addTag(tagName1);
        tagService.addTag(tagName2);
        tagService.addTag(tagName3);
        videoService.postVideo(userId1, new VideoDTO(videoId1, videoName1, videoViews1), tags1);
        videoService.postVideo(userId1, new VideoDTO(videoId2, videoName2, videoViews2), tags2);
        videoService.postVideo(userId1, new VideoDTO(videoId3, videoName3, videoViews3), tags2);
        videoService.postVideo(userId1, new VideoDTO(videoId4, videoName4, videoViews4), tags3);
        subscriptionService.subscribeUserToTag(tagName1, userId2);
        videoService.watchVideo(videoId2, userId2);
        videoService.likeVideo(videoId2, userId2);

        List<RecommendedVideoDTO> timeline = recommendationService.getUserTimeline(userId2);
        assertEquals(2, timeline.size());

        assertTrue(timeline.stream().anyMatch(video -> video.getId().equals(videoId1)));
        assertTrue(timeline.stream().anyMatch(video -> video.getId().equals(videoId3)));
    }
}