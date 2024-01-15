package com.example.controller;

import com.example.dto.RecommendedVideoDTO;
import com.example.service.RecommendationService;
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
public class RecommendationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecommendationController.class);

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    // Endpoint to get the timeline videos for a user
    @Get("/users/{userId}/timeline")
    public HttpResponse<Iterable<RecommendedVideoDTO>> getTimeline(
            @PathVariable(value = "userId") @NotEmpty String userId) {
        LOGGER.info("Fetching timeline for user ID: {}", userId);
        List<RecommendedVideoDTO> timeline = recommendationService.getUserTimeline(userId);

        if(timeline.isEmpty()) {
            LOGGER.info("No content found for user ID: {}", userId);
            return HttpResponse.noContent();
        }

        return HttpResponse.ok(timeline);
    }

    // Endpoint to get video recommendations for a user
    @Get("/users/{userId}/videos/recommendations")
    public HttpResponse<Iterable<RecommendedVideoDTO>> getRecommendations(
            @PathVariable(value = "userId") @NotEmpty String userId) {
        LOGGER.info("Fetching video recommendations for user ID: {}", userId);
        List<RecommendedVideoDTO> recommendations = recommendationService.getUserRecommendations(userId);

        if(recommendations.isEmpty()) {
            LOGGER.info("No recommendations found for user ID: {}", userId);
            return HttpResponse.noContent();
        }

        return HttpResponse.ok(recommendations);
    }

    // Endpoint to get video recommendations for a user based on a specific tag
    @Get("/users/{userId}/tags/{tagName}/videos/recommendations")
    public HttpResponse<Iterable<RecommendedVideoDTO>> getRecommendations(
            @PathVariable(value = "userId") @NotEmpty String userId,
            @PathVariable(value = "tagName") @NotEmpty String tagName) {
        LOGGER.info("Fetching recommendations for user ID: {} and tag: {}", userId, tagName);
        List<RecommendedVideoDTO> recommendations = recommendationService.getUserRecommendations(userId, tagName);

        if(recommendations.isEmpty()) {
            LOGGER.info("No recommendations found for user ID: {} and tag: {}", userId, tagName);
            return HttpResponse.noContent();
        }

        return HttpResponse.ok(recommendations);
    }
}
