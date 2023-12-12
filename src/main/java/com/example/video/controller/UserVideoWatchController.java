package com.example.video.controller;

import com.example.video.dto.UserVideoWatchDTO;
import com.example.video.service.UserVideoWatchService;
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
public class UserVideoWatchController {
    @Inject
    private UserVideoWatchService userVideoWatchService;

    @Post(value = "/{user}/videos/{uid}/watch")
    public String watch(
            @PathVariable(value = "user") @NotEmpty String user,
            @PathVariable(value = "uid") @NotEmpty String videoId) {
        UserVideoWatchDTO uvw = new UserVideoWatchDTO();
        uvw.setUserId(user);
        uvw.setVideoId(UUID.fromString(videoId));
        userVideoWatchService.save(uvw);
        return "OK";
    }

    @Get(value = "/{user}/videos/{uid}/watch")
    public HttpResponse<String> getWatch(
            @PathVariable(value = "user") @NotEmpty String user,
            @PathVariable(value = "uid") @NotEmpty String videoId) {
        Optional<UserVideoWatchDTO> uvw = userVideoWatchService.findById(user, UUID.fromString(videoId));
        if (uvw.isEmpty()) return HttpResponse.notFound("Not Found");
        return HttpResponse.ok("Found");
    }
}
