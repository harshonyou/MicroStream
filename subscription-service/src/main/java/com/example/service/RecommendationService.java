package com.example.service;

import com.example.dto.RecommendedVideoDTO;

import java.util.List;

public interface RecommendationService {
    List<RecommendedVideoDTO> getUserTimeline(String userId);

    List<RecommendedVideoDTO> getUserRecommendations(String userId);

    List<RecommendedVideoDTO> getUserRecommendations(String userId, String tagName);
}
