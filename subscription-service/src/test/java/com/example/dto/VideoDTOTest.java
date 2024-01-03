package com.example.dto;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class VideoDTOTest {
    @Test
    public void testSetAndGetId() {
        VideoDTO videoDTO = new VideoDTO();
        UUID testId = UUID.randomUUID();

        videoDTO.setId(testId);
        assertEquals(testId, videoDTO.getId(), "setId or getId does not work correctly");
    }

    @Test
    public void testSetAndGetTitle() {
        VideoDTO videoDTO = new VideoDTO();
        String testTitle = "Example Title";

        videoDTO.setTitle(testTitle);
        assertEquals(testTitle, videoDTO.getTitle(), "setTitle or getTitle does not work correctly");
    }

    @Test
    public void testSetAndGetViews() {
        VideoDTO videoDTO = new VideoDTO();
        Long testViews = 1000L;

        videoDTO.setViews(testViews);
        assertEquals(testViews, videoDTO.getViews(), "setViews or getViews does not work correctly");
    }

    @Test
    public void testNoArgsConstructor() {
        VideoDTO videoDTO = new VideoDTO();
        assertNull(videoDTO.getId(), "NoArgsConstructor should initialize id as null");
        assertNull(videoDTO.getTitle(), "NoArgsConstructor should initialize title as null");
        assertNull(videoDTO.getViews(), "NoArgsConstructor should initialize views as null");
    }

    @Test
    public void testAllArgsConstructor() {
        UUID testId = UUID.randomUUID();
        String testTitle = "Example Title";
        Long testViews = 1000L;
        VideoDTO videoDTO = new VideoDTO(testId, testTitle, testViews);

        assertEquals(testId, videoDTO.getId(), "AllArgsConstructor does not set id correctly");
        assertEquals(testTitle, videoDTO.getTitle(), "AllArgsConstructor does not set title correctly");
        assertEquals(testViews, videoDTO.getViews(), "AllArgsConstructor does not set views correctly");
    }
}