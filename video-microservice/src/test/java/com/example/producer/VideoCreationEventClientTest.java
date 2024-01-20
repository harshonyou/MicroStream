package com.example.producer;

import com.example.dto.VideoCreationEventDTO;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@MicronautTest(startApplication = false, environments = "no-streams")
class VideoCreationEventClientTest {
    private final Map<String, VideoCreationEventDTO> videoCreation = new HashMap<>();

    @MockBean(VideoCreationEventClient.class)
    VideoCreationEventClient testClient() {
        return videoCreation::put;
    }

    @AfterEach
    public void tearDown() {
        videoCreation.clear();
    }

    @Test
    public void testNotifyOnNewVideoPosted() {
        String userId = "user";
        UUID videoId = UUID.randomUUID();
        String title = "title";
        Set<String> tags = new HashSet<>();
        tags.add("tag1");
        tags.add("tag2");


        VideoCreationEventDTO event = new VideoCreationEventDTO(userId, videoId, title, tags);

        VideoCreationEventClient client = testClient();
        client.notifyOnVideoCreationEvent("key", event);

        assertEquals(1, videoCreation.size());
        assertTrue(videoCreation.containsKey("key"));
        assertEquals(event, videoCreation.get("key"));
    }
}