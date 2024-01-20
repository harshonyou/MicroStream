package com.example.service;

import com.example.dto.RecommendedVideoDTO;
import com.example.repository.VideoRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Singleton
public class RecommendationServiceImpl implements RecommendationService{

    private static final Logger LOGGER = LoggerFactory.getLogger(RecommendationServiceImpl.class);

    private final VideoRepository videoRepository;

    public RecommendationServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public List<RecommendedVideoDTO> getUserTimeline(String userId) {
        LOGGER.info("Fetching timeline for user ID: {}", userId);
        return videoRepository.getUserTimeline(userId);
    }

    @Override
    public List<RecommendedVideoDTO> getUserRecommendations(String userId) {
        LOGGER.info("Fetching recommendations for user ID: {}", userId);
        return videoRepository.getUserRecommendations(userId);
    }

    @Override
    public List<RecommendedVideoDTO> getUserRecommendations(String userId, String tagName) {
        LOGGER.info("Fetching recommendations for user ID: {} with tag: {}", userId, tagName);
        return videoRepository.getUserRecommendationsByTag(userId, tagName);
    }
}
