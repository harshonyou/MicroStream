package com.example.model;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false, environments = "no-streams")
class VideoFeedbackTest {
    private VideoFeedback videoFeedback;

    @BeforeEach
    public void setUp() {
        videoFeedback = new VideoFeedback();
    }

    @Test
    public void testToString() {
        videoFeedback.setUserId("testUser");
        videoFeedback.setVideoId(UUID.randomUUID());
        videoFeedback.setLikeStatus(true);

        String expectedToString = "VideoFeedback(userId=testUser, videoId=" + videoFeedback.getVideoId() + ", likeStatus=true)";
        assertEquals(expectedToString, videoFeedback.toString());
    }

    @Test
    public void testSetAndGetUserId() {
        videoFeedback.setUserId("testUser");
        assertEquals("testUser", videoFeedback.getUserId());
    }

    @Test
    public void testSetAndGetVideoId() {
        UUID videoId = UUID.randomUUID();
        videoFeedback.setVideoId(videoId);
        assertEquals(videoId, videoFeedback.getVideoId());
    }

    @Test
    public void testSetAndGetLikeStatus() {
        videoFeedback.setLikeStatus(true);
        assertTrue(videoFeedback.isLikeStatus());

        videoFeedback.setLikeStatus(false);
        assertFalse(videoFeedback.isLikeStatus());
    }

    @Test
    public void testDefaultConstructor() {
        VideoFeedback defaultFeedback = new VideoFeedback();
        assertNull(defaultFeedback.getUserId());
        assertNull(defaultFeedback.getVideoId());
        assertFalse(defaultFeedback.isLikeStatus());
    }
}