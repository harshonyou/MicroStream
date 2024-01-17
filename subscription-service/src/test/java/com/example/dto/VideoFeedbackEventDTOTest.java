package com.example.dto;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class VideoFeedbackEventDTOTest {
    @Test
    public void testSetAndGetUserId() {
        VideoFeedbackEventDTO videoFeedbackEventDTO = new VideoFeedbackEventDTO();
        String testUserId = "User123";

        videoFeedbackEventDTO.setUserId(testUserId);
        assertEquals(testUserId, videoFeedbackEventDTO.getUserId(), "setUserId or getUserId does not work correctly");
    }

    @Test
    public void testSetAndGetVideoId() {
        VideoFeedbackEventDTO videoFeedbackEventDTO = new VideoFeedbackEventDTO();
        UUID testVideoId = UUID.randomUUID();

        videoFeedbackEventDTO.setVideoId(testVideoId);
        assertEquals(testVideoId, videoFeedbackEventDTO.getVideoId(), "setVideoId or getVideoId does not work correctly");
    }

    @Test
    public void testSetAndGetLikeStatus() {
        VideoFeedbackEventDTO videoFeedbackEventDTO = new VideoFeedbackEventDTO();
        boolean testLikeStatus = true;

        videoFeedbackEventDTO.setLikeStatus(testLikeStatus);
        assertEquals(testLikeStatus, videoFeedbackEventDTO.isLikeStatus(), "setLikeStatus or isLikeStatus does not work correctly");
    }

    @Test
    public void testAllArgsConstructor() {
        String testUserId = "User123";
        UUID testVideoId = UUID.randomUUID();
        boolean testLikeStatus = true;

        VideoFeedbackEventDTO videoFeedbackEventDTO = new VideoFeedbackEventDTO(testUserId, testVideoId, testLikeStatus);

        assertEquals(testUserId, videoFeedbackEventDTO.getUserId(), "Constructor does not set userId correctly");
        assertEquals(testVideoId, videoFeedbackEventDTO.getVideoId(), "Constructor does not set videoId correctly");
        assertEquals(testLikeStatus, videoFeedbackEventDTO.isLikeStatus(), "Constructor does not set likeStatus correctly");
    }
}