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
import java.util.UUID;

@Validated
@Controller("/api/v1")
public class VideoTagController {
    @Inject
    private VideoTagService tagService;

    @Get(value = "/videos/{videoId}/tags/")
    public HttpResponse<Iterable<VideoTagDTO>> findTags(
            @PathVariable(value = "videoId") @NotEmpty String videoId) {
        List<VideoTagDTO> videoTagDTOList = tagService.search(UUID.fromString(videoId));

        if(videoTagDTOList.isEmpty()) return HttpResponse.notFound();
        return HttpResponse.ok(videoTagDTOList);
    }
}
