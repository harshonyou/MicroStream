package com.example.model;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false, environments = "no-streams")
class VideoTagTest {
    private VideoTag videoTag;

    @BeforeEach
    public void setUp() {
        videoTag = new VideoTag();
    }

    @Test
    public void testSetAndGetTag() {
        videoTag.setTag("TestTag");
        assertEquals("TestTag", videoTag.getTag());
    }

    @Test
    public void testSetAndGetVideoId() {
        UUID videoId = UUID.randomUUID();
        videoTag.setVideoId(videoId);
        assertEquals(videoId, videoTag.getVideoId());
    }

    @Test
    public void testDefaultConstructor() {
        VideoTag defaultTag = new VideoTag();
        assertNull(defaultTag.getTag());
        assertNull(defaultTag.getVideoId());
    }
}