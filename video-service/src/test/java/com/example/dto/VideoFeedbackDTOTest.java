package com.example.dto;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false, environments = "no-streams")
class VideoFeedbackDTOTest {
    private VideoFeedbackDTO dto;

    @BeforeEach
    public void setUp() {
        dto = new VideoFeedbackDTO("testUser", UUID.randomUUID(), true);
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
    public void testSetAndGetLikeStatus() {
        dto.setLikeStatus(false);
        assertFalse(dto.isLikeStatus());

        dto.setLikeStatus(true);
        assertTrue(dto.isLikeStatus());
    }

    @Test
    public void testToString() {
        String expectedToString = "VideoFeedbackDTO(userId=testUser, videoId=" + dto.getVideoId() + ", likeStatus=true)";
        assertEquals(expectedToString, dto.toString());
    }

    @Test
    public void testDefaultConstructor() {
        VideoFeedbackDTO defaultDTO = new VideoFeedbackDTO(null, null, false);
        assertNull(defaultDTO.getUserId());
        assertNull(defaultDTO.getVideoId());
        assertFalse(defaultDTO.isLikeStatus());
    }
}