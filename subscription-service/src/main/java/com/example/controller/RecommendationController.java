package com.example.controller;

import com.example.dto.RecommendedVideoDTO;
import com.example.repository.VideoRepository;
import com.example.service.RecommendationService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Controller("/api/v1")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @Get("/users/{userId}/timeline")
    public List<RecommendedVideoDTO> getTimeline(
            @PathVariable(value = "userId") @NotEmpty String userId) {
        return recommendationService.getUserTimeline(userId);
    }

    @Get("/users/{userId}/videos/recommendations")
    public List<RecommendedVideoDTO> getRecommendations(
            @PathVariable(value = "userId") @NotEmpty String userId) {
        return recommendationService.getUserRecommendations(userId);
    }

    @Get("/users/{userId}/tags/{tagName}/videos/recommendations")
    public List<RecommendedVideoDTO> getRecommendations(
            @PathVariable(value = "userId") @NotEmpty String userId,
            @PathVariable(value = "tagName") @NotEmpty String tagName) {
        return recommendationService.getUserRecommendations(userId, tagName);
    }
}
