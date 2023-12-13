package com.example.video.service;

import com.example.video.dto.VideoDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VideoService {
    VideoDTO save(VideoDTO videoDto);
    Optional<VideoDTO> findById(String userId, UUID videoId);
    List<VideoDTO> findByUser(String userId);
    void deleteById(String userId, UUID videoId);
    void deleteByUser(String userId);
    void deleteAll();
}
