package com.example.consumer;

import com.example.dto.*;
import com.example.helper.producer.VideoCreationEventClient;
import com.example.helper.producer.VideoEngagementEventClient;
import com.example.helper.producer.VideoFeedbackEventClient;
import com.example.model.Tag;
import com.example.model.User;
import com.example.model.Video;
import com.example.repository.Neo4jTagRepository;
import com.example.repository.Neo4jUserRepository;
import com.example.repository.Neo4jVideoRepository;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false)
class KafkaMessageListenerTest {
    @Inject
    Neo4jVideoRepository videoRepository;

    @Inject
    Neo4jTagRepository tagRepository;

    @Inject
    Neo4jUserRepository userRepository;

    @Inject
    VideoCreationEventClient videoCreationEventClient;

    @Inject
    VideoEngagementEventClient videoEngagementEventClient;

    @Inject
    VideoFeedbackEventClient videoFeedbackEventClient;

    @AfterEach
    public void tearDown() {
        videoRepository.deleteAll();
        tagRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void testHandleVideoCreationEvents() {
        String userId = "123";
        UUID videoId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        String title = "title";
        Set<String> tags = Set.of("action", "drama");

        videoCreationEventClient.send(
                userId,
                new VideoCreationEventDTO(
                        userId,
                        videoId,
                        title,
                        tags
                )
        );

        Awaitility.await().atMost(2, TimeUnit.MINUTES)
                .until(() -> videoRepository.findById(videoId).isPresent());

        Optional<User> user = userRepository.findById(userId);
        assertTrue(user.isPresent());
        assertEquals(userId, user.get().getId());

        Optional<Video> video = videoRepository.findById(videoId);
        assertTrue(video.isPresent());
        assertEquals(videoId, video.get().getId());
        assertEquals(title, video.get().getTitle());
        assertEquals(0L, video.get().getViews());

        Optional<Tag> actionTag = tagRepository.findByTagName("action");
        assertTrue(actionTag.isPresent());
        assertEquals("action", actionTag.get().getName());

        Optional<Tag> dramaTag = tagRepository.findByTagName("drama");
        assertTrue(dramaTag.isPresent());
        assertEquals("drama", dramaTag.get().getName());
    }

    @Test
    public void testHandleVideoEngagementEvents() {
        String userId1 = "user-id-1";
        String userName1 = "user-name-1";
        String userId2 = "user-id-2";
        String userName2 = "user-name-2";
        UUID videoId1 = UUID.fromString("00000000-0000-0000-0000-000000000000");
        String videoName1 = "video-name-1";
        Long videoViews1 = 0L;
        Set<Tag> tags1 = Set.of(new Tag("tag-name-1"));
        userRepository.addUser(new User(userId1, userName1));
        userRepository.addUser(new User(userId2, userName2));
        tagRepository.addTag(tags1.iterator().next());
        videoRepository.postVideo(userId1, new Video(videoId1, videoName1, videoViews1), tags1);

        videoEngagementEventClient.send(
                userId2,
                new VideoEngagementEventDTO(
                        userId2,
                        videoId1,
                        Instant.now()
                )
        );

        Awaitility.await().atMost(2, TimeUnit.MINUTES)
                .until(() -> {
                    Optional<Video> video = videoRepository.findById(videoId1);
                    return video.isPresent() && video.get().getViews() == 1L;
                });

        Optional<Video> video = videoRepository.findById(videoId1);
        assertTrue(video.isPresent());
        assertEquals(videoId1, video.get().getId());
        assertEquals(videoName1, video.get().getTitle());
        assertEquals(1L, video.get().getViews());
    }

    @Test
    public void testHandleVideoFeedbackEvents() { // User needs to subscribe to at-least one tag for timeline to recommend liked videos' tags
        String userId1 = "user-id-1";
        String userName1 = "user-name-1";
        String userId2 = "user-id-2";
        String userName2 = "user-name-2";
        UUID videoId1 = UUID.fromString("00000000-0000-0000-0000-000000000000");
        String videoName1 = "video-name-1";
        Long videoViews1 = 0L;
        UUID videoId2 = UUID.fromString("00000000-0000-0000-0000-000000000001");
        String videoName2 = "video-name-2";
        Long videoViews2 = 0L;
        String tagName1 = "tag-name-1";
        String tagName2 = "tag-name-2";
        Set<Tag> tags1 = Set.of(new Tag(tagName1));
        Set<Tag> tags2 = Set.of(new Tag(tagName2));

        userRepository.addUser(new User(userId1, userName1));
        userRepository.addUser(new User(userId2, userName2));

        tagRepository.addTag(tags1.iterator().next());
        tagRepository.addTag(tags2.iterator().next());

        videoRepository.postVideo(userId1, new Video(videoId1, videoName1, videoViews1), tags1);
        videoRepository.postVideo(userId1, new Video(videoId2, videoName2, videoViews2), tags1);

        tagRepository.associateUserWithTag(tagName2, userId2);

        videoFeedbackEventClient.send(
                userId2,
                new VideoFeedbackEventDTO(
                        userId2,
                        videoId1,
                        true
                )
        );

        videoFeedbackEventClient.send(
                userId2,
                new VideoFeedbackEventDTO(
                        userId1,
                        videoId1,
                        false
                )
        );

        Awaitility.await().atMost(2, TimeUnit.MINUTES)
                .until(() -> !videoRepository.getUserTimeline(userId2).isEmpty());

        List<RecommendedVideoDTO> recommendations = videoRepository.getUserTimeline(userId2);
        assertEquals(1, recommendations.size());
        assertEquals(videoId2, recommendations.get(0).getId());
        assertEquals(videoName2, recommendations.get(0).getTitle());
        assertEquals(Set.of(tagName1), recommendations.get(0).getAffiliatedTags());
        assertEquals(0L, recommendations.get(0).getViews());
    }
}