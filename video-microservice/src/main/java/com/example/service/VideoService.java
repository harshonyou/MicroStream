package com.example.service;

import com.example.dto.VideoDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VideoService {
    VideoDTO post(VideoDTO videoDto);
    Optional<VideoDTO> search(UUID videoId);
    Optional<VideoDTO> fetch(String userId, UUID videoId);
    List<VideoDTO> getUserPosts(String userId);
    void remove(String userId, UUID videoId);
    void removeUserPosts(String userId);
    void removeAllPosts();
}
