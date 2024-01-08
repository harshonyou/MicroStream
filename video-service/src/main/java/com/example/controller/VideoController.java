package com.example.controller;

import com.example.dto.VideoDTO;
import com.example.service.VideoService;
import com.example.util.ForTesting;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Validated
@Controller("/api/v1")
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @Get(value = "/users/{userId}/videos/")
    public HttpResponse<Iterable<VideoDTO>> findAllByUser(
            @PathVariable(value = "userId") @NotEmpty String userId) {
        List<VideoDTO> videos = videoService.getUserPosts(userId);
        if(videos.isEmpty()) return HttpResponse.notFound();
        return HttpResponse.ok(videos);
    }

    @Get(value = "/users/{userId}/videos/{videoId}")
    public HttpResponse<VideoDTO> findById(
            @PathVariable(value = "userId") @NotEmpty String userId,
            @PathVariable(value = "videoId") @NotEmpty String videoId) {
        Optional<VideoDTO> e = videoService.fetch(userId, UUID.fromString(videoId));
        if (e.isEmpty()) return HttpResponse.notFound();
        return HttpResponse.ok(e.get());
    }

    @Post(value = "/users/{userId}/videos/")
    public HttpResponse<VideoDTO> create( // TODO: Make Tag one word
            @PathVariable(value = "userId") @NotEmpty String userId,
            @Body @NotNull VideoDTO video) {
        if(video.getVideoId() != null) {
            Optional<VideoDTO> e = videoService.fetch(userId, video.getVideoId());
            if (e.isPresent()) return HttpResponse.badRequest();
        }

        video.setUserId(userId);
        video = videoService.post(video);
        return HttpResponse.created(video);
    }

    @Deprecated
    @Patch(value = "/users/{userId}/videos/{videoId}") // TODO: PUT?
    public HttpResponse<VideoDTO> update(
            @PathVariable(value = "userId") @NotEmpty String userId,
            @PathVariable(value = "videoId") @NotEmpty String videoId,
            @Body @NotNull VideoDTO video) {
        Optional<VideoDTO> e = videoService.fetch(userId, UUID.fromString(videoId));
        if (e.isEmpty()) return HttpResponse.notFound();
        // TODO: Maybe also update tags? from the tag DB
        video.setUserId(userId);
        video.setVideoId(UUID.fromString(videoId));
        video = videoService.post(video);
        return HttpResponse.ok(video);
    }

    @ForTesting
    @Delete(value = "/users/{userId}/videos/")
    public HttpResponse<Void> deleteAllByUser(
            @PathVariable(value = "userId") @NotEmpty String userId) {
        videoService.removeUserPosts(userId);
        return HttpResponse.noContent();
    }

    @ForTesting
    @Delete(value = "/users/{userId}/videos/{videoId}")
    public HttpResponse<Void> deleteById(
            @PathVariable(value = "userId") @NotEmpty String userId,
            @PathVariable(value = "videoId") @NotEmpty String videoId) {
        Optional<VideoDTO> e = videoService.fetch(userId, UUID.fromString(videoId));
        if (e.isEmpty()) return HttpResponse.notFound();
        videoService.remove(userId, UUID.fromString(videoId));
        return HttpResponse.noContent();
    }

}
