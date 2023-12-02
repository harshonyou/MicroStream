package com.example.video.controller;

import com.datastax.oss.driver.api.core.CqlSession;
import com.example.video.dto.VideoDTO;
import com.example.video.service.VideoService;
import com.example.video.model.Video;
import com.example.video.repository.VideoRepository;
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

@Validated
@Controller("/api/v1")
public class VideoController {

    @Inject
    private CqlSession cqlSession;

    private VideoService repo;

    @Get(value = "/{user}/videos/")
    public List<Video> findAllByUser(
            @PathVariable(value = "user") @NotEmpty String user) {
        return getVideoService().findByUser(user).stream()
                .map(this::fromDto)
                .collect(Collectors.toList());
    }

    @Get(value = "/{user}/videos/{uid}")
    public HttpResponse<Video> findById(
            @PathVariable(value = "user") @NotEmpty String user,
            @PathVariable(value = "uid") @NotEmpty String videoId) {
        Optional<VideoDTO> e = getVideoService().findById(user, UUID.fromString(videoId));
        if (e.isEmpty()) return HttpResponse.notFound();
        return HttpResponse.ok(fromDto(e.get()));
    }

    @Post(value = "/{user}/videos/")
    public HttpResponse<Video> create(
            @PathVariable(value = "user") @NotEmpty String user,
            @Body @NotNull Video videoReq) {
        VideoDTO video = fromEntity(videoReq, user);
        video = getVideoService().save(video);
        videoReq.setUserId(video.getUserId());
        return HttpResponse.created(videoReq);
    }

    public VideoService getVideoService() {
        if (null == repo) {
            repo = new VideoRepository(cqlSession);
        }
        return repo;
    }

    private Video fromDto(VideoDTO dto) {
        Video v = new Video();
        v.setUserId(dto.getUserId());
        v.setVideoId(dto.getVideoId());
        v.setTitle(dto.getTitle());
        v.setWatched(dto.getWatched());
        return v;
    }

    private VideoDTO fromEntity(Video v, String user) {
        VideoDTO dto = new VideoDTO();
        dto.setUserId(user);
        dto.setVideoId(v.getVideoId());
        dto.setTitle(v.getTitle());
        dto.setWatched(v.getWatched());
        return dto;
    }

}
