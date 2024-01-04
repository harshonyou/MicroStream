package com.example.video.controller;

import com.example.video.dto.VideoTagDTO;
import com.example.video.service.VideoTagService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Validated
@Controller("/api/v1")
public class VideoTagController {
    @Inject
    private VideoTagService tagService;

    @Get(value = "/videos/tags/{tagName}/")
    public HttpResponse<Iterable<VideoTagDTO>> findByTag(
            @PathVariable(value = "tagName") @NotEmpty String tagName) {
        List<VideoTagDTO> videos = tagService.searchVideos(tagName);
        if(videos.isEmpty()) return HttpResponse.notFound();
        return HttpResponse.ok(videos);
    }
}
