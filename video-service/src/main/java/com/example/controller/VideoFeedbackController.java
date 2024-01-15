package com.example.controller;

import com.example.dto.VideoFeedbackDTO;
import com.example.service.VideoFeedbackService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.UUID;

@Validated
@Controller("/api/v1")
public class VideoFeedbackController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VideoFeedbackController.class);

    private final VideoFeedbackService feedbackService;

    public VideoFeedbackController(VideoFeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    // Endpoint for liking a video
    @Post(value = "/users/{userId}/videos/{videoId}/like")
    public HttpResponse<VideoFeedbackDTO> like(
            @PathVariable(value = "userId") String userId,
            @PathVariable(value = "videoId") String videoId) {
        LOGGER.info("User ID: {} liking video ID: {}", userId, videoId);
        Optional<VideoFeedbackDTO> saved = feedbackService.submitFeedback(userId, UUID.fromString(videoId), true);

        if (saved.isEmpty()) {
            LOGGER.info("Video ID: {} not found for like operation", videoId);
            return HttpResponse.notFound();
        }

        return HttpResponse.ok(saved.get());
    }

    // Endpoint for disliking a video
    @Post(value = "/users/{userId}/videos/{videoId}/dislike")
    public HttpResponse<VideoFeedbackDTO> dislike(
            @PathVariable(value = "userId") String userId,
            @PathVariable(value = "videoId") String videoId) {
        LOGGER.info("User ID: {} disliking video ID: {}", userId, videoId);
        Optional<VideoFeedbackDTO> saved = feedbackService.submitFeedback(userId, UUID.fromString(videoId), false);

        if (saved.isEmpty()) {
            LOGGER.info("Video ID: {} not found for dislike operation", videoId);
            return HttpResponse.notFound();
        }

        return HttpResponse.ok(saved.get());
    }
}
