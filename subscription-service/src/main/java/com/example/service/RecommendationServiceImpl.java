package com.example.service;

import com.example.dto.RecommendedVideoDTO;
import com.example.repository.VideoRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class RecommendationServiceImpl implements RecommendationService{
    @Inject
    private VideoRepository videoRepository;

    @Override
    public List<RecommendedVideoDTO> getUserTimeline(String userId) {
        return videoRepository.getUserTimeline(userId);
    }

    @Override
    public List<RecommendedVideoDTO> getUserRecommendations(String userId) {
        return videoRepository.getUserRecommendations(userId);
    }

    @Override
    public List<RecommendedVideoDTO> getUserRecommendations(String userId, String tagName) {
        return videoRepository.getUserRecommendationsByTag(userId, tagName);
    }
}
