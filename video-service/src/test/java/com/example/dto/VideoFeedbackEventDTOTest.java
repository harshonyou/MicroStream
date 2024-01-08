package com.example.dto;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false, environments = "no-streams")
class VideoFeedbackEventDTOTest {
    private VideoFeedbackEventDTO dto;

    @BeforeEach
    public void setUp() {
        Set<String> tags = new HashSet<>();
        tags.add("Tag1");
        tags.add("Tag2");
        dto = new VideoFeedbackEventDTO("testUser", UUID.randomUUID(), tags, true);
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
    public void testSetAndGetTags() {
        Set<String> newTags = new HashSet<>();
        newTags.add("Tag3");
        newTags.add("Tag4");
        dto.setTags(newTags);
        assertEquals(newTags, dto.getTags());
    }

    @Test
    public void testSetAndGetLikeStatus() {
        dto.setLikeStatus(false);
        assertFalse(dto.isLikeStatus());

        dto.setLikeStatus(true);
        assertTrue(dto.isLikeStatus());
    }

    @Test
    public void testDefaultConstructor() {
        VideoFeedbackEventDTO defaultDTO = new VideoFeedbackEventDTO(null, null, null, false);
        assertNull(defaultDTO.getUserId());
        assertNull(defaultDTO.getVideoId());
        assertNull(defaultDTO.getTags());
        assertFalse(defaultDTO.isLikeStatus());
    }

    @Test
    public void testNoArgConstructor() {
        VideoFeedbackEventDTO noArgDTO = new VideoFeedbackEventDTO();
        assertNull(noArgDTO.getUserId());
        assertNull(noArgDTO.getVideoId());
        assertNull(noArgDTO.getTags());
        assertFalse(noArgDTO.isLikeStatus());
    }
}