package com.example.controller;

import com.example.dto.VideoTagDTO;
import com.example.service.VideoTagService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.validation.Validated;
import jakarta.validation.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Validated
@Controller("/api/v1")
public class VideoTagController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoTagController.class);

    private final VideoTagService tagService;

    public VideoTagController(VideoTagService tagService) {
        this.tagService = tagService;
    }

    // Endpoint to find videos by a specific tag
    @Get(value = "/videos/tags/{tagName}/")
    public HttpResponse<Iterable<VideoTagDTO>> findByTag(
            @PathVariable(value = "tagName") @NotEmpty String tagName) {
        LOGGER.info("Fetching videos for tag: {}", tagName);
        List<VideoTagDTO> videos = tagService.searchVideos(tagName);

        if(videos.isEmpty()) {
            LOGGER.info("No videos found for tag: {}", tagName);
            return HttpResponse.notFound();
        }

        return HttpResponse.ok(videos);
    }
}
