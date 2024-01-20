package com.example.controller;

import com.example.dto.VideoDTO;
import com.example.service.VideoService;
import com.example.util.ForTesting;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Validated
@Controller("/api/v1")
public class VideoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoController.class);

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    // Endpoint to find all videos by a user
    @Get(value = "/users/{userId}/videos/")
    public HttpResponse<Iterable<VideoDTO>> findAllByUser(
            @PathVariable(value = "userId") @NotEmpty String userId) {
        LOGGER.info("Fetching all videos for user ID: {}", userId);
        List<VideoDTO> videos = videoService.getUserPosts(userId);

        if(videos.isEmpty()) {
            LOGGER.info("No videos found for user ID: {}", userId);
            return HttpResponse.notFound();
        }

        return HttpResponse.ok(videos);
    }

    // Endpoint to find a specific video by ID
    @Get(value = "/users/{userId}/videos/{videoId}")
    public HttpResponse<VideoDTO> findById(
            @PathVariable(value = "userId") @NotEmpty String userId,
            @PathVariable(value = "videoId") @NotEmpty String videoId) {
        LOGGER.info("Fetching video with ID: {} for user ID: {}", videoId, userId);
        Optional<VideoDTO> e = videoService.fetch(userId, UUID.fromString(videoId));

        if (e.isEmpty()) {
            LOGGER.info("Video with ID: {} not found for user ID: {}", videoId, userId);
            return HttpResponse.notFound();
        }

        return HttpResponse.ok(e.get());
    }

    // Endpoint to create a new video
    @Post(value = "/users/{userId}/videos/")
    public HttpResponse<VideoDTO> create(
            @PathVariable(value = "userId") @NotEmpty String userId,
            @Body @NotNull VideoDTO video) {
        LOGGER.info("Creating new video for user ID: {}", userId);
        if(video.getVideoId() != null) {
            Optional<VideoDTO> e = videoService.fetch(userId, video.getVideoId());

            if (e.isPresent()) {
                LOGGER.info("Video with ID: {} already exists for user ID: {}", video.getVideoId(), userId);
                return HttpResponse.badRequest();
            }
        }

        video.setUserId(userId);
        video = videoService.post(video);
        return HttpResponse.created(video);
    }

    // Deprecated endpoint to update a video
    @Deprecated
    @Patch(value = "/users/{userId}/videos/{videoId}")
    public HttpResponse<VideoDTO> update(
            @PathVariable(value = "userId") @NotEmpty String userId,
            @PathVariable(value = "videoId") @NotEmpty String videoId,
            @Body @NotNull VideoDTO video) {
        LOGGER.info("Updating video with ID: {} for user ID: {}", videoId, userId);
        Optional<VideoDTO> e = videoService.fetch(userId, UUID.fromString(videoId));
        if (e.isEmpty()) return HttpResponse.notFound();
        video.setUserId(userId);
        video.setVideoId(UUID.fromString(videoId));
        video = videoService.post(video);
        return HttpResponse.ok(video);
    }

    // Endpoint to delete all videos by a user (for testing)
    @ForTesting
    @Delete(value = "/users/{userId}/videos/")
    public HttpResponse<Void> deleteAllByUser(
            @PathVariable(value = "userId") @NotEmpty String userId) {
        LOGGER.info("Deleting all videos for user ID: {}", userId);
        videoService.removeUserPosts(userId);
        return HttpResponse.noContent();
    }

    // Endpoint to delete a specific video by ID (for testing)
    @ForTesting
    @Delete(value = "/users/{userId}/videos/{videoId}")
    public HttpResponse<Void> deleteById(
            @PathVariable(value = "userId") @NotEmpty String userId,
            @PathVariable(value = "videoId") @NotEmpty String videoId) {
        LOGGER.info("Deleting video with ID: {} for user ID: {}", videoId, userId);
        Optional<VideoDTO> e = videoService.fetch(userId, UUID.fromString(videoId));
        if (e.isEmpty()) return HttpResponse.notFound();
        videoService.remove(userId, UUID.fromString(videoId));
        return HttpResponse.noContent();
    }

}
