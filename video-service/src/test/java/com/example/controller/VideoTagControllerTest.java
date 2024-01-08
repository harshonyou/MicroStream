package com.example.controller;

import com.example.dto.VideoTagDTO;
import com.example.service.VideoTagService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@MicronautTest(environments = "no-streams")
class VideoTagControllerTest {
    @Mock
    VideoTagService mockVideoTagService;

    VideoTagController videoTagController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        videoTagController = new VideoTagController(mockVideoTagService);
    }


    @Test
    public void testFindByTag() {
        String tagName = "tag1";

        List<VideoTagDTO> videoTags = new ArrayList<>();
        VideoTagDTO videoTag1 = new VideoTagDTO();
        videoTag1.setTag(tagName);
        videoTags.add(videoTag1);

        when(mockVideoTagService.searchVideos("tag1")).thenReturn(videoTags);

        HttpResponse<Iterable<VideoTagDTO>> response = videoTagController.findByTag(tagName);

        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals(videoTags, response.getBody().get());

        when(mockVideoTagService.searchVideos("tag1")).thenReturn(new ArrayList<>());

        response = videoTagController.findByTag(tagName);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
    }
}

