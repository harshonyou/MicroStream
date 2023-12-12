package com.example.video.service;

import com.example.video.dto.UserVideoWatchDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserVideoWatchService {
    UserVideoWatchDTO save(UserVideoWatchDTO userVideoWatchDto);

    Optional<UserVideoWatchDTO> findById(String userId, UUID videoId);

    List<UserVideoWatchDTO> findByUser(String userId);

    List<UserVideoWatchDTO> findByVideo(UUID videoId);
}
