package com.example.service;

import com.example.dto.RecommendedVideoDTO;
import com.example.dto.TagDTO;
import com.example.dto.VideoDTO;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface VideoService {

    void postVideo(String userId, VideoDTO video, Set<TagDTO> tags);

    void likeVideo(UUID videoId, String userId);

    void dislikeVideo(UUID videoId, String userId);

    void watchVideo(UUID videoId, String userId);
}
