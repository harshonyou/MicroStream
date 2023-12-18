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

    @Get("/timeline/{userId}")
    public List<VideoRecommendationDTO> getTimeline(int userId) {
        return videoRepository.getUserTimeline(userId);
    }

    @Get("/recommendations/{userId}")
    public List<VideoRecommendationDTO> getRecommendations(int userId) {
        return videoRepository.recommendVideos(userId);
    }

    @Get("/recommendations/{userId}/{tag}")
    public List<VideoRecommendationDTO> getRecommendations(int userId, String tag) {
        return videoRepository.recommendVideosByTag(userId, tag);
    }
}
