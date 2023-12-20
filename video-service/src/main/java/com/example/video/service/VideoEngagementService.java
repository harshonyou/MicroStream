package com.example.video.service;

import com.example.video.dto.VideoEngagementDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VideoEngagementService {
    VideoEngagementDTO save(VideoEngagementDTO userVideoWatchDto);

    Optional<VideoEngagementDTO> findById(String userId, UUID videoId);

    List<VideoEngagementDTO> findByUser(String userId); // TODO: Make them iterable

    List<VideoEngagementDTO> findByVideo(UUID videoId);
}
