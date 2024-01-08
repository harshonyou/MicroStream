package com.example.dto;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false, environments = "no-streams")
class VideoTagDTOTest {
    private VideoTagDTO dto;

    @BeforeEach
    public void setUp() {
        dto = new VideoTagDTO();
    }

    @Test
    public void testToString() {
        dto.setTag("TestTag");
        dto.setVideoId(UUID.randomUUID());

        String expectedToString = "VideoTagDTO(tag=TestTag, videoId=" + dto.getVideoId() + ")";
        assertEquals(expectedToString, dto.toString());
    }

    @Test
    public void testSetAndGetTag() {
        dto.setTag("NewTestTag");
        assertEquals("NewTestTag", dto.getTag());
    }

    @Test
    public void testSetAndGetVideoId() {
        UUID videoId = UUID.randomUUID();
        dto.setVideoId(videoId);
        assertEquals(videoId, dto.getVideoId());
    }

    @Test
    public void testDefaultConstructor() {
        VideoTagDTO defaultDTO = new VideoTagDTO();
        assertNull(defaultDTO.getTag());
        assertNull(defaultDTO.getVideoId());
    }
}