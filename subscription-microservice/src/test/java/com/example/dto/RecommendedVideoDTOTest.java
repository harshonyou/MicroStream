package com.example.dto;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RecommendedVideoDTOTest {
    @Test
    public void testSetAndGetId() {
        RecommendedVideoDTO video = new RecommendedVideoDTO();
        UUID testId = UUID.randomUUID();

        video.setId(testId);
        assertEquals(testId, video.getId(), "setId or getId does not work correctly");
    }

    @Test
    public void testSetAndGetTitle() {
        RecommendedVideoDTO video = new RecommendedVideoDTO();
        String testTitle = "Test Video";

        video.setTitle(testTitle);
        assertEquals(testTitle, video.getTitle(), "setTitle or getTitle does not work correctly");
    }

    @Test
    public void testSetAndGetAffiliatedTags() {
        RecommendedVideoDTO video = new RecommendedVideoDTO();
        Set<String> testTags = new HashSet<>();
        testTags.add("Adventure");
        testTags.add("Action");

        video.setAffiliatedTags(testTags);
        assertEquals(testTags, video.getAffiliatedTags(), "setAffiliatedTags or getAffiliatedTags does not work correctly");
    }

    @Test
    public void testSetAndGetViews() {
        RecommendedVideoDTO video = new RecommendedVideoDTO();
        Long testViews = 1000L;

        video.setViews(testViews);
        assertEquals(testViews, video.getViews(), "setViews or getViews does not work correctly");
    }

    @Test
    public void testAllArgsConstructor() {
        UUID testId = UUID.randomUUID();
        String testTitle = "Test Video";
        Set<String> testTags = new HashSet<>();
        testTags.add("Adventure");
        testTags.add("Action");
        Long testViews = 1000L;

        RecommendedVideoDTO video = new RecommendedVideoDTO(testId, testTitle, testTags, testViews);

        assertEquals(testId, video.getId(), "Constructor does not set id correctly");
        assertEquals(testTitle, video.getTitle(), "Constructor does not set title correctly");
        assertEquals(testTags, video.getAffiliatedTags(), "Constructor does not set affiliatedTags correctly");
        assertEquals(testViews, video.getViews(), "Constructor does not set views correctly");
    }
}