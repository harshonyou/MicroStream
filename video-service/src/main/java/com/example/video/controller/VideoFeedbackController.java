package com.example.video.controller;

import com.example.video.dto.VideoFeedbackDTO;
import com.example.video.service.VideoFeedbackService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;

import java.util.Optional;
import java.util.UUID;

@Validated
@Controller("/api/v1")
public class VideoFeedbackController {

    @Inject
    private VideoFeedbackService feedbackService;

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
