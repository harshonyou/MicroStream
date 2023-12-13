package com.example.video.controller;

import com.example.video.service.LikeVideoService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;

import java.util.UUID;

@Validated
@Controller("/api/v1")
public class LikeVideoController {

    @Inject
    private LikeVideoService likeVideoService;

    @Post(value = "/{user}/videos/{uid}/like")
    public String like(
            @PathVariable(value = "user") String user,
            @PathVariable(value = "uid") String videoId) {
        likeVideoService.save(user, UUID.fromString(videoId), true);
        return "OK";
    }

    @Post(value = "/{user}/videos/{uid}/dislike")
    public String dislike(
            @PathVariable(value = "user") String user,
            @PathVariable(value = "uid") String videoId) {
        likeVideoService.save(user, UUID.fromString(videoId), false);
        return "OK";
    }
}
