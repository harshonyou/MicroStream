package com.example.video.controller;

import com.example.video.dto.VideoDTO;
import com.example.video.service.VideoServiceImpl;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Validated
@Controller("/api/v1")
public class VideoController {

    @Inject
    private VideoServiceImpl videoManager;

    @Get(value = "/users/{userId}/videos/")
    public List<VideoDTO> findAllByUser(
            @PathVariable(value = "userId") @NotEmpty String userId) {
        return videoManager.findByUser(userId);
    }

    @Get(value = "/users/{userId}/videos/{videoId}")
    public HttpResponse<VideoDTO> findById(
            @PathVariable(value = "userId") @NotEmpty String userId,
            @PathVariable(value = "videoId") @NotEmpty String videoId) {
        Optional<VideoDTO> e = videoManager.findById(userId, UUID.fromString(videoId));
        if (e.isEmpty()) return HttpResponse.notFound();
        return HttpResponse.ok(e.get());
    }

    @Post(value = "/users/{userId}/videos/")
    public HttpResponse<VideoDTO> create( // TODO: When creating a post based on the video ID, the tags does not work
            @PathVariable(value = "userId") @NotEmpty String userId,
            @Body @NotNull VideoDTO video) {
        video.setUserId(userId);
        video = videoManager.save(video);
        return HttpResponse.created(video);
    }

    @Patch(value = "/users/{userId}/videos/{videoId}") // PUT?
    public HttpResponse<VideoDTO> update(
            @PathVariable(value = "userId") @NotEmpty String userId,
            @PathVariable(value = "videoId") @NotEmpty String videoId,
            @Body @NotNull VideoDTO video) {
        Optional<VideoDTO> e = videoManager.findById(userId, UUID.fromString(videoId));
        if (e.isEmpty()) return HttpResponse.notFound();
        // TODO: Maybe also update tags? from the tag DB
        video.setUserId(userId);
        video.setVideoId(UUID.fromString(videoId));
        video = videoManager.save(video);
        return HttpResponse.ok(video);
    }

    @Delete(value = "/users/{userId}/videos/")
    public HttpResponse<Void> deleteAllByUser(
            @PathVariable(value = "userId") @NotEmpty String userId) {
        videoManager.deleteByUser(userId);
        return HttpResponse.noContent();
    }

    @Delete(value = "/users/{userId}/videos/{videoId}")
    public HttpResponse<Void> deleteById(
            @PathVariable(value = "userId") @NotEmpty String userId,
            @PathVariable(value = "videoId") @NotEmpty String videoId) {
        Optional<VideoDTO> e = videoManager.findById(userId, UUID.fromString(videoId));
        if (e.isEmpty()) return HttpResponse.notFound();
        videoManager.deleteById(userId, UUID.fromString(videoId));
        return HttpResponse.noContent();
    }

}
