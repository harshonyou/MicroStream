package com.example.dto;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false, environments = "no-streams")
class VideoCreationEventDTOTest {
    private VideoCreationEventDTO dto;

    @BeforeEach
    public void setUp() {
        dto = new VideoCreationEventDTO("testUser", UUID.randomUUID(), "Test Video", new HashSet<>());
    }

    @Test
    public void testNoArgConstructor() {
        VideoCreationEventDTO noArgDTO = new VideoCreationEventDTO();
        assertNull(noArgDTO.getUserId());
        assertNull(noArgDTO.getVideoId());
        assertNull(noArgDTO.getTitle());
        assertNull(noArgDTO.getTags());
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
        VideoCreationEventDTO defaultDTO = new VideoCreationEventDTO(null, null, null, null);
        assertNull(defaultDTO.getUserId());
        assertNull(defaultDTO.getVideoId());
        assertNull(defaultDTO.getTitle());
        assertNull(defaultDTO.getTags());
    }
}