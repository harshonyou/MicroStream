package com.example.controller;

import com.example.dto.VideoFeedbackDTO;
import com.example.service.VideoFeedbackService;
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
import static org.mockito.Mockito.when;

@MicronautTest(environments = "no-streams")
class VideoFeedbackControllerTest {
    @Mock
    VideoFeedbackService mockVideoFeedbackService;

    VideoFeedbackController videoFeedbackController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        videoFeedbackController = new VideoFeedbackController(mockVideoFeedbackService);
    }

    @Test
    public void testLike() {
        String userId = "user1";
        String videoId = "a861d54d-4c8d-4b9d-af2b-1a12c53d7b9e";
        UUID uuid = UUID.fromString(videoId);

        VideoFeedbackDTO videoFeedbackDTO = new VideoFeedbackDTO(userId, uuid, true);
        Optional<VideoFeedbackDTO> savedFeedback = Optional.of(videoFeedbackDTO);

        when(mockVideoFeedbackService.submitFeedback(userId, uuid, true)).thenReturn(savedFeedback);

        HttpResponse<VideoFeedbackDTO> response = videoFeedbackController.like(userId, videoId);

        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals(savedFeedback.get(), response.getBody().get());
    }

    @Test
    public void testLikeNotFound() {
        String userId = "user1";
        String videoId = "a861d54d-4c8d-4b9d-af2b-1a12c53d7b9e";
        UUID uuid = UUID.fromString(videoId);

        when(mockVideoFeedbackService.submitFeedback(userId, uuid, true)).thenReturn(Optional.empty());

        HttpResponse<VideoFeedbackDTO> response = videoFeedbackController.like(userId, videoId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
    }

    @Test
    public void testDislike() {
        String userId = "user1";
        String videoId = "a861d54d-4c8d-4b9d-af2b-1a12c53d7b9e";
        UUID uuid = UUID.fromString(videoId);

        VideoFeedbackDTO videoFeedbackDTO = new VideoFeedbackDTO(userId, uuid, false);
        Optional<VideoFeedbackDTO> savedFeedback = Optional.of(videoFeedbackDTO);

        when(mockVideoFeedbackService.submitFeedback(userId, uuid, false)).thenReturn(savedFeedback);

        HttpResponse<VideoFeedbackDTO> response = videoFeedbackController.dislike(userId, videoId);

        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals(savedFeedback.get(), response.getBody().get());
    }

    @Test
    public void testDislikeNotFound() {
        String userId = "user1";
        String videoId = "a861d54d-4c8d-4b9d-af2b-1a12c53d7b9e";
        UUID uuid = UUID.fromString(videoId);

        when(mockVideoFeedbackService.submitFeedback(userId, uuid, false)).thenReturn(Optional.empty());

        HttpResponse<VideoFeedbackDTO> response = videoFeedbackController.dislike(userId, videoId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
    }
}

