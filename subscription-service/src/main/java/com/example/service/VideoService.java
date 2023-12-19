package com.example.service;

import com.example.dto.RecommendedVideoDTO;
import com.example.dto.TagDTO;
import com.example.dto.VideoDTO;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface VideoService {

    default void postVideo(VideoDTO video) {
        postVideo(video);
    }

    void postVideo(String userId, VideoDTO video, Set<TagDTO> tags);

    void likeVideo(UUID videoId, String userId);

    void watchVideo(UUID videoId, String userId);

    List<RecommendedVideoDTO> getTimeline(String userId);

    List<RecommendedVideoDTO> getRecommendations(String userId);

    List<RecommendedVideoDTO> getRecommendations(String userId, String tag);
}
