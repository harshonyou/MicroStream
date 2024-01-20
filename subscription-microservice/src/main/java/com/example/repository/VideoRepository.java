package com.example.repository;

import com.example.dto.RecommendedVideoDTO;
import com.example.model.Tag;
import com.example.model.Video;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface VideoRepository {
    void postVideo(String userId, Video video, Set<Tag> tags);

    void likeVideo(UUID videoId, String userId);

    boolean isLiked(UUID videoId, String userId);

    void dislikeVideo(UUID videoId, String userId);

    void incrementVideoViews(UUID videoId);

    void watchVideo(UUID videoId, String userId);

    List<RecommendedVideoDTO> getUserTimeline(String userId);

    List<RecommendedVideoDTO> getUserRecommendations(String userId);

    List<RecommendedVideoDTO> getUserRecommendationsByTag(String userId, String tagName);
}
