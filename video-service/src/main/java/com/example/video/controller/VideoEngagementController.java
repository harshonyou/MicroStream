package com.example.video.controller;

import com.example.video.dto.VideoEngagementDTO;
import com.example.video.service.VideoEngagementService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotEmpty;

import java.util.Optional;
import java.util.UUID;

@Validated
@Controller("/api/v1")
public class VideoEngagementController {
    @Inject
    private VideoEngagementService engagementService;

    @Post(value = "/users/{userId}/videos/{videoId}/watch")
    public HttpResponse<VideoEngagementDTO> watch(
            @PathVariable(value = "userId") @NotEmpty String userId,
            @PathVariable(value = "videoId") @NotEmpty String videoId) {
        VideoEngagementDTO uvw = new VideoEngagementDTO();
        uvw.setUserId(userId);
        uvw.setVideoId(UUID.fromString(videoId));
        Optional<VideoEngagementDTO> saved = engagementService.markVideoWatched(uvw);
        if (saved.isEmpty()) return HttpResponse.notFound();
        return HttpResponse.ok(saved.get());
    }

    @Get(value = "/users/{userId}/videos/{videoId}/watch")
    public HttpResponse<VideoEngagementDTO> getWatch(
            @PathVariable(value = "userId") @NotEmpty String userId,
            @PathVariable(value = "videoId") @NotEmpty String videoId) {
        Optional<VideoEngagementDTO> uvw = engagementService.findWatchStatus(userId, UUID.fromString(videoId));
        if (uvw.isEmpty()) return HttpResponse.notFound();
        return HttpResponse.ok(uvw.get());
    }
}
