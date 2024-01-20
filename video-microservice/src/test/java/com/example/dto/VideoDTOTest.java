package com.example.dto;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false, environments = "no-streams")
class VideoDTOTest {
    private VideoDTO dto;

    @BeforeEach
    public void setUp() {
        dto = new VideoDTO();
    }

    @Test
    public void testToString() {
        dto.setUserId("testUser");
        dto.setVideoId(UUID.randomUUID());
        dto.setTitle("Test Video");
        Set<String> tags = new HashSet<>();
        tags.add("Tag1");
        tags.add("Tag2");
        dto.setTags(tags);

        String expectedToString = "VideoDTO(userId=testUser, videoId=" + dto.getVideoId() + ", title=Test Video, tags=[Tag1, Tag2])";
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
    public void testSetAndGetTitle() {
        dto.setTitle("New Test Video");
        assertEquals("New Test Video", dto.getTitle());
    }

    @Test
    public void testSetAndGetTags() {
        Set<String> tags = new HashSet<>();
        tags.add("Tag1");
        tags.add("Tag2");
        dto.setTags(tags);
        assertEquals(tags, dto.getTags());
    }

    @Test
    public void testDefaultConstructor() {
        VideoDTO defaultDTO = new VideoDTO();
        assertNull(defaultDTO.getUserId());
        assertNull(defaultDTO.getVideoId());
        assertNull(defaultDTO.getTitle());
        assertNull(defaultDTO.getTags());
    }
}

