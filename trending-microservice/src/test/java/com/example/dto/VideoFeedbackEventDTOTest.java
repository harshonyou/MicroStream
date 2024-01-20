package com.example.dto;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false, environments = "no-streams")
class VideoFeedbackEventDTOTest {
    @Test
    void testNoArgsConstructorAndSetters() {
        VideoFeedbackEventDTO dto = new VideoFeedbackEventDTO();
        Set<String> tags = new HashSet<>();
        tags.add("tag1");
        tags.add("tag2");
        dto.setTags(tags);
        dto.setLikeStatus(true);

        assertEquals(tags, dto.getTags());
        assertTrue(dto.isLikeStatus());
    }

    @Test
    void testAllArgsConstructorAndGetters() {
        Set<String> expectedTags = new HashSet<>();
        expectedTags.add("tag1");
        expectedTags.add("tag2");
        VideoFeedbackEventDTO dto = new VideoFeedbackEventDTO(expectedTags, true);

        assertEquals(expectedTags, dto.getTags());
        assertTrue(dto.isLikeStatus());
    }
}