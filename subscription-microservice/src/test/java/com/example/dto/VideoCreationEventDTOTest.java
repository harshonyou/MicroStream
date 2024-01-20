package com.example.dto;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class VideoCreationEventDTOTest {
    @Test
    public void testSetAndGetUserId() {
        VideoCreationEventDTO videoEvent = new VideoCreationEventDTO();
        String testUserId = "User123";

        videoEvent.setUserId(testUserId);
        assertEquals(testUserId, videoEvent.getUserId(), "setUserId or getUserId does not work correctly");
    }

    @Test
    public void testSetAndGetVideoId() {
        VideoCreationEventDTO videoEvent = new VideoCreationEventDTO();
        UUID testVideoId = UUID.randomUUID();

        videoEvent.setVideoId(testVideoId);
        assertEquals(testVideoId, videoEvent.getVideoId(), "setVideoId or getVideoId does not work correctly");
    }

    @Test
    public void testSetAndGetTitle() {
        VideoCreationEventDTO videoEvent = new VideoCreationEventDTO();
        String testTitle = "Test Video";

        videoEvent.setTitle(testTitle);
        assertEquals(testTitle, videoEvent.getTitle(), "setTitle or getTitle does not work correctly");
    }

    @Test
    public void testSetAndGetTags() {
        VideoCreationEventDTO videoEvent = new VideoCreationEventDTO();
        Set<String> testTags = new HashSet<>();
        testTags.add("Adventure");
        testTags.add("Action");

        videoEvent.setTags(testTags);
        assertEquals(testTags, videoEvent.getTags(), "setTags or getTags does not work correctly");
    }

    @Test
    public void testAllArgsConstructor() {
        String testUserId = "User123";
        UUID testVideoId = UUID.randomUUID();
        String testTitle = "Test Video";
        Set<String> testTags = new HashSet<>();
        testTags.add("Adventure");
        testTags.add("Action");

        VideoCreationEventDTO videoEvent = new VideoCreationEventDTO(testUserId, testVideoId, testTitle, testTags);

        assertEquals(testUserId, videoEvent.getUserId(), "Constructor does not set userId correctly");
        assertEquals(testVideoId, videoEvent.getVideoId(), "Constructor does not set videoId correctly");
        assertEquals(testTitle, videoEvent.getTitle(), "Constructor does not set title correctly");
        assertEquals(testTags, videoEvent.getTags(), "Constructor does not set tags correctly");
    }
}