package com.example.controller;

import com.example.dto.VideoEngagementDTO;
import com.example.service.VideoEngagementService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@MicronautTest( environments = "no-streams")
class VideoEngagementControllerTest {
    @Mock
    VideoEngagementService mockVideoEngagementService;

    VideoEngagementController videoEngagementController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        videoEngagementController = new VideoEngagementController(mockVideoEngagementService);
    }

    @Test
    public void testWatch() {
        String userId = "user1";
        String videoId = "a861d54d-4c8d-4b9d-af2b-1a12c53d7b9e";

        VideoEngagementDTO videoEngagementDTO = new VideoEngagementDTO();
        videoEngagementDTO.setUserId(userId);
        videoEngagementDTO.setVideoId(UUID.fromString(videoId));
        Optional<VideoEngagementDTO> savedEngagement = Optional.of(videoEngagementDTO);

        when(mockVideoEngagementService.markVideoWatched(any(VideoEngagementDTO.class))).thenReturn(savedEngagement);

        HttpResponse<VideoEngagementDTO> response = videoEngagementController.watch(userId, videoId);

        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals(savedEngagement.get(), response.getBody().get());
    }

    @Test
    public void testWatchNotFound() {
        String userId = "user1";
        String videoId = "a861d54d-4c8d-4b9d-af2b-1a12c53d7b9e";

        VideoEngagementDTO videoEngagementDTO = new VideoEngagementDTO();
        videoEngagementDTO.setUserId(userId);
        videoEngagementDTO.setVideoId(UUID.fromString(videoId));

        when(mockVideoEngagementService.markVideoWatched(videoEngagementDTO)).thenReturn(Optional.empty());

        HttpResponse<VideoEngagementDTO> response = videoEngagementController.watch(userId, videoId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
    }

    @Test
    public void testGetWatch() {
        String userId = "user1";
        String videoId = "a861d54d-4c8d-4b9d-af2b-1a12c53d7b9e";

        VideoEngagementDTO videoEngagementDTO = new VideoEngagementDTO();
        videoEngagementDTO.setUserId(userId);
        videoEngagementDTO.setVideoId(UUID.fromString(videoId));
        Optional<VideoEngagementDTO> savedEngagement = Optional.of(videoEngagementDTO);

        when(mockVideoEngagementService.findWatchStatus(userId, UUID.fromString(videoId))).thenReturn(savedEngagement);

        HttpResponse<VideoEngagementDTO> response = videoEngagementController.getWatch(userId, videoId);

        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals(savedEngagement.get(), response.getBody().get());
    }

    @Test
    public void testGetWatchNotFound() {
        String userId = "user1";
        String videoId = "a861d54d-4c8d-4b9d-af2b-1a12c53d7b9e";

        when(mockVideoEngagementService.findWatchStatus(userId, UUID.fromString(videoId))).thenReturn(Optional.empty());

        HttpResponse<VideoEngagementDTO> response = videoEngagementController.getWatch(userId, videoId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
    }
}

