package com.example.controller;

import com.example.dto.VideoEngagementDTO;
import com.example.service.VideoEngagementService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import jakarta.validation.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.UUID;

@Validated
@Controller("/api/v1")
public class VideoEngagementController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoEngagementController.class);

    private final VideoEngagementService engagementService;

    public VideoEngagementController(VideoEngagementService engagementService) {
        this.engagementService = engagementService;
    }

    // Endpoint to mark a video as watched by a user
    @Post(value = "/users/{userId}/videos/{videoId}/watch")
    public HttpResponse<VideoEngagementDTO> watch(
            @PathVariable(value = "userId") @NotEmpty String userId,
            @PathVariable(value = "videoId") @NotEmpty String videoId) {
        LOGGER.info("Marking video ID: {} as watched by user ID: {}", videoId, userId);
        VideoEngagementDTO uvw = new VideoEngagementDTO();
        uvw.setUserId(userId);
        uvw.setVideoId(UUID.fromString(videoId));
        Optional<VideoEngagementDTO> saved = engagementService.markVideoWatched(uvw);

        if (saved.isEmpty()) {
            LOGGER.info("Video ID: {} not found for user ID: {}", videoId, userId);
            return HttpResponse.notFound();
        }

        return HttpResponse.ok(saved.get());
    }

    // Endpoint to get watch status of a video for a user
    @Get(value = "/users/{userId}/videos/{videoId}/watch")
    public HttpResponse<VideoEngagementDTO> getWatch(
            @PathVariable(value = "userId") @NotEmpty String userId,
            @PathVariable(value = "videoId") @NotEmpty String videoId) {
        LOGGER.info("Fetching watch status for video ID: {} and user ID: {}", videoId, userId);
        Optional<VideoEngagementDTO> uvw = engagementService.findWatchStatus(userId, UUID.fromString(videoId));

        if (uvw.isEmpty()) {
            LOGGER.info("Watch status not found for video ID: {} and user ID: {}", videoId, userId);
            return HttpResponse.notFound();
        }

        return HttpResponse.ok(uvw.get());
    }
}
