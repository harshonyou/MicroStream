package com.example.dto;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false, environments = "no-streams")
class VideoEngagementDTOTest {
    private VideoEngagementDTO dto;

    @BeforeEach
    public void setUp() {
        dto = new VideoEngagementDTO();
    }

    @Test
    public void testToString() {
        dto.setUserId("testUser");
        dto.setVideoId(UUID.randomUUID());
        dto.setWatchedTime(Instant.now());

        String expectedToString = "VideoEngagementDTO(userId=testUser, videoId=" + dto.getVideoId() + ", watchedTime=" + dto.getWatchedTime() + ")";
        assertEquals(expectedToString, dto.toString());
    }

    @Test
    public void testSetAndGetUserId() {
        dto.setUserId("newUser");
        assertEquals("newUser", dto.getUserId());
    }

    @Test
    public void testSetAndGetVideoId() {
        UUID videoId = UUID.randomUUID();
        dto.setVideoId(videoId);
        assertEquals(videoId, dto.getVideoId());
    }

    @Test
    public void testSetAndGetWatchedTime() {
        Instant watchedTime = Instant.now();
        dto.setWatchedTime(watchedTime);
        assertEquals(watchedTime, dto.getWatchedTime());
    }

    @Test
    public void testDefaultConstructor() {
        VideoEngagementDTO defaultDTO = new VideoEngagementDTO();
        assertNull(defaultDTO.getUserId());
        assertNull(defaultDTO.getVideoId());
        assertNull(defaultDTO.getWatchedTime());
    }
}

