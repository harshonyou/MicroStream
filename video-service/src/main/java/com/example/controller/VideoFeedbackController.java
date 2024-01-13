package com.example.controller;

import com.example.dto.VideoFeedbackDTO;
import com.example.service.VideoFeedbackService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;

import java.util.Optional;
import java.util.UUID;

@Validated
@Controller("/api/v1")
public class VideoFeedbackController {

    private final VideoFeedbackService feedbackService;

    public VideoFeedbackController(VideoFeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @Post(value = "/users/{userId}/videos/{videoId}/like")
    public HttpResponse<VideoFeedbackDTO> like(
            @PathVariable(value = "userId") String userId,
            @PathVariable(value = "videoId") String videoId) {
        Optional<VideoFeedbackDTO> saved = feedbackService.submitFeedback(userId, UUID.fromString(videoId), true);

        if (saved.isEmpty()) return HttpResponse.notFound();
        return HttpResponse.ok(saved.get());
    }

    @Post(value = "/users/{userId}/videos/{videoId}/dislike")
    public HttpResponse<VideoFeedbackDTO> dislike(
            @PathVariable(value = "userId") String userId,
            @PathVariable(value = "videoId") String videoId) {
        Optional<VideoFeedbackDTO> saved = feedbackService.submitFeedback(userId, UUID.fromString(videoId), false);

        if (saved.isEmpty()) return HttpResponse.notFound();
        return HttpResponse.ok(saved.get());
    }
}