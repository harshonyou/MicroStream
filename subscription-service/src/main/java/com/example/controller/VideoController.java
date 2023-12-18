package com.example.controller;

import com.example.dto.RecommendedVideoDTO;
import com.example.model.Video;
import com.example.repository.VideoRepository;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import java.util.List;

@Controller("/api/v1")
public class VideoController {

    private final VideoRepository videoRepository;

    public VideoController(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Get("/timeline/{userId}")
    public List<RecommendedVideoDTO> getTimeline(String userId) {
        return videoRepository.getUserTimeline(userId);
    }

    @Get("/recommendations/{userId}")
    public List<RecommendedVideoDTO> getRecommendations(String userId) {
        return videoRepository.recommendVideos(userId);
    }

    @Get("/recommendations/{userId}/{tag}")
    public List<RecommendedVideoDTO> getRecommendations(String userId, String tag) {
        return videoRepository.recommendVideosByTag(userId, tag);
    }
}
