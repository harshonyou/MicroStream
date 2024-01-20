package com.example.model;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false, environments = "no-streams")
class UserEngagementTest {
    private UserEngagement userEngagement;

    @BeforeEach
    public void setUp() {
        userEngagement = new UserEngagement();
    }

    @Test
    public void testSetAndGetUserId() {
        userEngagement.setUserId("testUser");
        assertEquals("testUser", userEngagement.getUserId());
    }

    @Test
    public void testSetAndGetVideoId() {
        UUID videoId = UUID.randomUUID();
        userEngagement.setVideoId(videoId);
        assertEquals(videoId, userEngagement.getVideoId());
    }

    @Test
    public void testSetAndGetWatchedTime() {
        Instant watchedTime = Instant.now();
        userEngagement.setWatchedTime(watchedTime);
        assertEquals(watchedTime, userEngagement.getWatchedTime());
    }

    @Test
    public void testDefaultConstructor() {
        UserEngagement defaultEngagement = new UserEngagement();
        assertNull(defaultEngagement.getUserId());
        assertNull(defaultEngagement.getVideoId());
        assertNull(defaultEngagement.getWatchedTime());
    }
}