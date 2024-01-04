package com.example.controller;

import com.example.dto.RecommendedVideoDTO;
import com.example.service.RecommendationService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Controller("/api/v1")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @Get("/users/{userId}/timeline")
    public HttpResponse<Iterable<RecommendedVideoDTO>> getTimeline(
            @PathVariable(value = "userId") @NotEmpty String userId) {
        List<RecommendedVideoDTO> timeline = recommendationService.getUserTimeline(userId);
        if(timeline.isEmpty())
            return HttpResponse.noContent();

        return HttpResponse.ok(timeline);
    }

    @Get("/users/{userId}/videos/recommendations")
    public HttpResponse<Iterable<RecommendedVideoDTO>> getRecommendations(
            @PathVariable(value = "userId") @NotEmpty String userId) {
        List<RecommendedVideoDTO> recommendations = recommendationService.getUserRecommendations(userId);
        if(recommendations.isEmpty())
            return HttpResponse.noContent();

        return HttpResponse.ok(recommendations);
    }

    @Get("/users/{userId}/tags/{tagName}/videos/recommendations")
    public HttpResponse<Iterable<RecommendedVideoDTO>> getRecommendations(
            @PathVariable(value = "userId") @NotEmpty String userId,
            @PathVariable(value = "tagName") @NotEmpty String tagName) {
        List<RecommendedVideoDTO> recommendations = recommendationService.getUserRecommendations(userId, tagName);
        if(recommendations.isEmpty())
            return HttpResponse.noContent();

        return HttpResponse.ok(recommendations);
    }
}
