package com.example.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class VideoTest {
    @Test
    public void testSetAndGetId() {
        Video video = new Video();
        UUID testId = UUID.randomUUID();

        video.setId(testId);
        assertEquals(testId, video.getId(), "setId or getId does not work correctly");
    }

    @Test
    public void testSetAndGetTitle() {
        Video video = new Video();
        String testTitle = "Test Video";

        video.setTitle(testTitle);
        assertEquals(testTitle, video.getTitle(), "setTitle or getTitle does not work correctly");
    }

    @Test
    public void testSetAndGetViews() {
        Video video = new Video();
        Long testViews = 1000L;

        video.setViews(testViews);
        assertEquals(testViews, video.getViews(), "setViews or getViews does not work correctly");
    }

    @Test
    public void testNoArgsConstructor() {
        Video video = new Video();
        assertNull(video.getId(), "NoArgsConstructor should initialize id as null");
        assertNull(video.getTitle(), "NoArgsConstructor should initialize title as null");
        assertNull(video.getViews(), "NoArgsConstructor should initialize views as null");
    }

    @Test
    public void testAllArgsConstructor() {
        UUID testId = UUID.randomUUID();
        String testTitle = "Test Video";
        Long testViews = 1000L;
        Video video = new Video(testId, testTitle, testViews);

        assertEquals(testId, video.getId(), "AllArgsConstructor does not set id correctly");
        assertEquals(testTitle, video.getTitle(), "AllArgsConstructor does not set title correctly");
        assertEquals(testViews, video.getViews(), "AllArgsConstructor does not set views correctly");
    }
}

