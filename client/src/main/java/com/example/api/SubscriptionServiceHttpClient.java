package com.example.api;

import com.example.dto.RecommendedVideoDTO;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

import java.util.List;

@Client("${subscription-service.api.url}")
public interface SubscriptionServiceHttpClient {

    @Post("/api/v1/users/{userId}/tags/{tagName}/subscribe")
    HttpResponse<Void> subscribe(
            @PathVariable(value = "userId") String userId,
            @PathVariable(value = "tagName") String tagName
    );

    @Post("/api/v1/users/{userId}/tags/{tagName}/unsubscribe")
    HttpResponse<Void> unsubscribe(
            @PathVariable(value = "userId") String userId,
            @PathVariable(value = "tagName") String tagName
    );

    @Get("/api/v1/users/{userId}/timeline")
    HttpResponse<List<RecommendedVideoDTO>> getTimeline(
            @PathVariable(value = "userId") String userId
    );

    @Get("/api/v1/users/{userId}/videos/recommendations")
    HttpResponse<List<RecommendedVideoDTO>> getRecommendations(
            @PathVariable(value = "userId") String userId
    );

    @Get("/api/v1/users/{userId}/tags/{tagName}/videos/recommendations")
    HttpResponse<List<RecommendedVideoDTO>> getRecommendations(
            @PathVariable(value = "userId") String userId,
            @PathVariable(value = "tagName") String tagName
    );
}
