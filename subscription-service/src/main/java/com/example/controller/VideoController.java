package com.example.controller;

import com.example.dto.VideoRecommendationDTO;
import com.example.repository.VideoRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

@Controller("/api/v1")
public class VideoController {

    private final VideoRepository videoRepository;

    public VideoController(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Get("/recommendations/{userId}/{tag}")
    public List<VideoRecommendationDTO> getRecommendations(int userId, String tag) {
        return videoRepository.recommendVideosByTag(userId, tag);
    }
}
