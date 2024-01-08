package com.example.controller;

import com.example.dto.VideoTagDTO;
import com.example.service.VideoTagService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.validation.Validated;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Validated
@Controller("/api/v1")
public class VideoTagController {
    private final VideoTagService tagService;

    public VideoTagController(VideoTagService tagService) {
        this.tagService = tagService;
    }

    @Get(value = "/videos/tags/{tagName}/")
    public HttpResponse<Iterable<VideoTagDTO>> findByTag(
            @PathVariable(value = "tagName") @NotEmpty String tagName) {
        List<VideoTagDTO> videos = tagService.searchVideos(tagName);
        if(videos.isEmpty()) return HttpResponse.notFound();
        return HttpResponse.ok(videos);
    }
}
