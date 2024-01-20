package com.example.dto;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false, environments = "no-streams")
class VideoEngagementEventDTOTest {
    private VideoEngagementEventDTO dto;

    @BeforeEach
    public void setUp() {
        dto = new VideoEngagementEventDTO("testUser", UUID.randomUUID(), Instant.now());
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
        VideoEngagementEventDTO defaultDTO = new VideoEngagementEventDTO(null, null, null);
        assertNull(defaultDTO.getUserId());
        assertNull(defaultDTO.getVideoId());
        assertNull(defaultDTO.getWatchedTime());
    }

    @Test
    public void testNoArgConstructor() {
        VideoEngagementEventDTO noArgDTO = new VideoEngagementEventDTO();
        assertNull(noArgDTO.getUserId());
        assertNull(noArgDTO.getVideoId());
        assertNull(noArgDTO.getWatchedTime());
    }
}

