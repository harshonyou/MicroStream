package com.example.producer;

import com.example.dto.VideoEngagementEventDTO;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest(startApplication = false, environments = "no-streams")
class VideoEngagementEventClientTest {
    private final Map<String, VideoEngagementEventDTO> videoEngagement = new HashMap<>();

    @MockBean(VideoEngagementEventClient.class)
    VideoEngagementEventClient testClient() {
        return videoEngagement::put;
    }

    @AfterEach
    public void tearDown() {
        videoEngagement.clear();
    }

    @Test
    public void testNotifyOnVideoWatched() {
        String userId = "user";
        UUID videoId = UUID.randomUUID();
        Instant watchedTime = Instant.now();
        VideoEngagementEventDTO event = new VideoEngagementEventDTO(userId, videoId, watchedTime);
        VideoEngagementEventClient client = testClient();
        client.notifyOnVideoEngagementEvent("key", event);
        assertEquals(1, videoEngagement.size());
        assertTrue(videoEngagement.containsKey("key"));
        assertEquals(event, videoEngagement.get("key"));
    }
}