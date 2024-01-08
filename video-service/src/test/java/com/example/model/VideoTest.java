package com.example.model;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false, environments = "no-streams")
class VideoTest {
    private Video video;

    @BeforeEach
    public void setUp() {
        video = new Video();
    }

    @Test
    public void testSetAndGetUserId() {
        video.setUserId("testUser");
        assertEquals("testUser", video.getUserId());
    }

    @Test
    public void testSetAndGetVideoId() {
        UUID videoId = UUID.randomUUID();
        video.setVideoId(videoId);
        assertEquals(videoId, video.getVideoId());
    }

    @Test
    public void testSetAndGetTitle() {
        video.setTitle("Test Video");
        assertEquals("Test Video", video.getTitle());
    }

    @Test
    public void testSetAndGetTags() {
        Set<String> tags = new HashSet<>();
        tags.add("Tag1");
        tags.add("Tag2");
        video.setTags(tags);
        assertEquals(tags, video.getTags());
    }

    @Test
    public void testDefaultConstructor() {
        Video defaultVideo = new Video();
        assertNull(defaultVideo.getUserId());
        assertNull(defaultVideo.getVideoId());
        assertNull(defaultVideo.getTitle());
        assertNull(defaultVideo.getTags());
    }
}