package com.example.service;

import com.example.dto.RecommendedVideoDTO;
import com.example.dto.VideoCreationEventDTO;
import com.example.dto.VideoDTO;

import java.util.List;
import java.util.UUID;

public interface VideoService {

    void postVideo(VideoDTO video);

    void likeVideo(UUID videoId, String userId);

    void watchVideo(UUID videoId, String userId);

    List<RecommendedVideoDTO> getTimeline(String userId);

    List<RecommendedVideoDTO> getRecommendations(String userId);

    List<RecommendedVideoDTO> getRecommendations(String userId, String tag);
}
