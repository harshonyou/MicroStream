package com.example.video.controller;

import com.example.video.service.VideoFeedbackService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;

import java.util.UUID;

@Validated
@Controller("/api/v1")
public class VideoFeedbackController {

    @Inject
    private VideoFeedbackService videoFeedbackService;

    @Post(value = "/users/{userId}/videos/{videoId}/like")
    public String like(
            @PathVariable(value = "userId") String userId,
            @PathVariable(value = "videoId") String videoId) {
        videoFeedbackService.save(userId, UUID.fromString(videoId), true);
        return "OK";
    }

    @Post(value = "/users/{userId}/videos/{videoId}/dislike")
    public String dislike(
            @PathVariable(value = "userId") String userId,
            @PathVariable(value = "videoId") String videoId) {
        videoFeedbackService.save(userId, UUID.fromString(videoId), false);
        return "OK";
    }
}
