package com.example.producer;

import com.example.dto.VideoFeedbackEventDTO;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest(startApplication = false, environments = "no-streams")
class VideoFeedbackEventClientTest {
    private final Map<String, VideoFeedbackEventDTO> videoFeedback = new HashMap<>();

    @MockBean(VideoFeedbackEventClient.class)
    VideoFeedbackEventClient testClient() {
        return videoFeedback::put;
    }

    @AfterEach
    public void tearDown() {
        videoFeedback.clear();
    }

    @Test
    public void testNotifyOnLikeDislike() {
        String userId = "user";
        UUID videoId = UUID.randomUUID();
        Set<String> tags = new HashSet<>();
        tags.add("tag1");
        tags.add("tag2");
        boolean likeStatus = true;

        VideoFeedbackEventDTO event = new VideoFeedbackEventDTO(userId, videoId, tags, likeStatus);

        VideoFeedbackEventClient client = testClient();
        client.notifyOnVideoFeedbackEvent("key", event);

        assertEquals(1, videoFeedback.size());
        assertTrue(videoFeedback.containsKey("key"));
        assertEquals(event, videoFeedback.get("key"));
    }
}