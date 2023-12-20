package com.example.video.controller;

import com.example.video.dto.VideoTagDTO;
import com.example.video.service.VideoTagService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Validated
@Controller("/api/v1")
public class VideoTagController {
    @Inject
    private VideoTagService videoTagService;

    @Get(value = "/videos/tags/{tagName}/")
    public List<VideoTagDTO> findByTag(
            @PathVariable(value = "tagName") @NotEmpty String tagName) {
        return videoTagService.findByTag(tagName);
    }
}
