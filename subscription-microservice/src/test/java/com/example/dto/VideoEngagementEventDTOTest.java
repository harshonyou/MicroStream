package com.example.dto;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class VideoEngagementEventDTOTest {
    @Test
    public void testSetAndGetUserId() {
        VideoEngagementEventDTO videoEventDTO = new VideoEngagementEventDTO();
        String testUserId = "User123";

        videoEventDTO.setUserId(testUserId);
        assertEquals(testUserId, videoEventDTO.getUserId(), "setUserId or getUserId does not work correctly");
    }

    @Test
    public void testSetAndGetVideoId() {
        VideoEngagementEventDTO videoEventDTO = new VideoEngagementEventDTO();
        UUID testVideoId = UUID.randomUUID();

        videoEventDTO.setVideoId(testVideoId);
        assertEquals(testVideoId, videoEventDTO.getVideoId(), "setVideoId or getVideoId does not work correctly");
    }

    @Test
    public void testSetAndGetWatchedTime() {
        VideoEngagementEventDTO videoEventDTO = new VideoEngagementEventDTO();
        Instant testWatchedTime = Instant.now();

        videoEventDTO.setWatchedTime(testWatchedTime);
        assertEquals(testWatchedTime, videoEventDTO.getWatchedTime(), "setWatchedTime or getWatchedTime does not work correctly");
    }

    @Test
    public void testAllArgsConstructor() {
        String testUserId = "User123";
        UUID testVideoId = UUID.randomUUID();
        Instant testWatchedTime = Instant.now();

        VideoEngagementEventDTO videoEventDTO = new VideoEngagementEventDTO(testUserId, testVideoId, testWatchedTime);

        assertEquals(testUserId, videoEventDTO.getUserId(), "Constructor does not set userId correctly");
        assertEquals(testVideoId, videoEventDTO.getVideoId(), "Constructor does not set videoId correctly");
        assertEquals(testWatchedTime, videoEventDTO.getWatchedTime(), "Constructor does not set watchedTime correctly");
    }
}