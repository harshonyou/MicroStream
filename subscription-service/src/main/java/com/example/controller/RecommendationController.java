package com.example.controller;

import com.example.dto.RecommendedVideoDTO;
import com.example.repository.VideoRepository;
import com.example.service.RecommendationService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

@Controller("/api/v1")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @Get("/timeline/{userId}")
    public List<RecommendedVideoDTO> getTimeline(String userId) {
        return recommendationService.getUserTimeline(userId);
    }

    @Get("/recommendations/{userId}")
    public List<RecommendedVideoDTO> getRecommendations(String userId) {
        return recommendationService.getUserRecommendations(userId);
    }

    @Get("/recommendations/{userId}/{tag}")
    public List<RecommendedVideoDTO> getRecommendations(String userId, String tag) {
        return recommendationService.getUserRecommendations(userId, tag);
    }
}
