package com.example.api;

import com.example.dto.VideoDTO;
import com.example.dto.VideoEngagementDTO;
import com.example.dto.VideoFeedbackDTO;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

import java.util.List;
import java.util.UUID;

@Client("${video-service.api.url}")
public interface VideoServiceHttpClient {
    @Post("/api/v1/users/{userId}/videos/")
    HttpResponse<VideoDTO> postVideo(
            @PathVariable String userId,
            @Body VideoDTO videoDTO
    );

    @Get("/api/v1/users/{userId}/videos/")
    HttpResponse<List<VideoDTO>> getVideosByUserId(@PathVariable String userId);

    @Post("/api/v1/users/{userId}/videos/{videoId}/watch")
    HttpResponse<VideoEngagementDTO> watchVideo(
            @PathVariable String userId,
            @PathVariable UUID videoId
    );

    @Post("/api/v1/users/{userId}/videos/{videoId}/like")
    HttpResponse<VideoFeedbackDTO> likeVideo(
            @PathVariable String userId,
            @PathVariable UUID videoId
    );

    @Post("/api/v1/users/{userId}/videos/{videoId}/dislike")
    HttpResponse<VideoFeedbackDTO> dislikeVideo(
            @PathVariable String userId,
            @PathVariable UUID videoId
    );
}
