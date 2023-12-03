package com.example.video.controller;

import com.example.video.dto.VideoDTO;
import com.example.video.service.UserManager;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Validated
@Controller("/api/v1")
public class VideoController {

    @Inject
    private UserManager userManager;

    @Get(value = "/{user}/videos/")
    public List<VideoDTO> findAllByUser(
            @PathVariable(value = "user") @NotEmpty String user) {
        return userManager.findByUser(user);
    }

    @Get(value = "/{user}/videos/{uid}")
    public HttpResponse<VideoDTO> findById(
            @PathVariable(value = "user") @NotEmpty String user,
            @PathVariable(value = "uid") @NotEmpty String videoId) {
        Optional<VideoDTO> e = userManager.findById(user, UUID.fromString(videoId));
        if (e.isEmpty()) return HttpResponse.notFound();
        return HttpResponse.ok(e.get());
    }

    @Post(value = "/{user}/videos/")
    public HttpResponse<VideoDTO> create(
            @PathVariable(value = "user") @NotEmpty String user,
            @Body @NotNull VideoDTO video) {
        video.setUserId(user);
        video = userManager.save(video);
        return HttpResponse.created(video);
    }

    @Patch(value = "/{user}/videos/{uid}")
    public HttpResponse<VideoDTO> update(
            @PathVariable(value = "user") @NotEmpty String user,
            @PathVariable(value = "uid") @NotEmpty String videoId,
            @Body @NotNull VideoDTO video) {
        Optional<VideoDTO> e = userManager.findById(user, UUID.fromString(videoId));
        if (e.isEmpty()) return HttpResponse.notFound();
        video.setUserId(user);
        video = userManager.save(video);
        return HttpResponse.ok(video);
    }

    @Delete(value = "/{user}/videos/")
    public HttpResponse<Void> deleteAllByUser(
            @PathVariable(value = "user") @NotEmpty String user) {
        userManager.deleteByUser(user);
        return HttpResponse.noContent();
    }

    @Delete(value = "/{user}/videos/{vid}")
    public HttpResponse<Void> deleteById(
            @PathVariable(value = "user") @NotEmpty String user,
            @PathVariable(value = "vid") @NotEmpty String videoId) {
        Optional<VideoDTO> e = userManager.findById(user, UUID.fromString(videoId));
        if (e.isEmpty()) return HttpResponse.notFound();
        userManager.deleteById(user, UUID.fromString(videoId));
        return HttpResponse.noContent();
    }

}
