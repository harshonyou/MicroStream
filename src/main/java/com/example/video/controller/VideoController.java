package com.example.video.controller;

import com.example.video.dto.VideoDTO;
import com.example.video.mapper.VideoMapper;
import com.example.video.service.UserManager;
import com.example.video.model.Video;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.video.mapper.VideoMapper.fromDto;
import static com.example.video.mapper.VideoMapper.fromEntity;

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
}
