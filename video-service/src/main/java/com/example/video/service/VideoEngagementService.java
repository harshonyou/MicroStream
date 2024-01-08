package com.example.video.service;

import com.example.video.dto.VideoEngagementDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VideoEngagementService {
    Optional<VideoEngagementDTO> markVideoWatched(VideoEngagementDTO userVideoWatchDto);

    Optional<VideoEngagementDTO> findWatchStatus(String userId, UUID videoId);

    List<VideoEngagementDTO> findWatchHistory(String userId); // TODO: Make them iterable
}
