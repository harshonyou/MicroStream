package com.example.service;

import com.example.dto.VideoDTO;
import com.example.producer.VideoEngagementEventClient;
import com.example.dto.VideoEngagementDTO;
import com.example.dto.VideoEngagementEventDTO;
import com.example.mapper.VideoEngagementMapper;
import com.example.model.UserEngagement;
import com.example.repository.CassandraVideoEngagementRepository;
import com.example.repository.VideoEngagementRepository;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.mapper.VideoEngagementMapper.fromDto;
import static com.example.mapper.VideoEngagementMapper.fromEntity;

@Singleton
public class VideoEngagementServiceImpl implements VideoEngagementService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoEngagementServiceImpl.class);

    private final VideoService videoService;

    private final VideoEngagementRepository engagementRepository;
    private final VideoEngagementEventClient eventClient;

    public VideoEngagementServiceImpl(VideoServiceImpl videoService,CassandraVideoEngagementRepository engagementRepository, VideoEngagementEventClient eventClient) {
        this.engagementRepository = engagementRepository;
        this.videoService = videoService;
        this.eventClient = eventClient;
    }

    // Marks a video as watched and notifies via event client
    @Override
    public Optional<VideoEngagementDTO> markVideoWatched(VideoEngagementDTO videoEngagementDTO) {
        LOGGER.info("Marking video ID: {} as watched by user ID: {}", videoEngagementDTO.getVideoId(), videoEngagementDTO.getUserId());
        Optional<VideoDTO> videoDTO = videoService.search(videoEngagementDTO.getVideoId());

        if(videoDTO.isEmpty()) {
            LOGGER.info("Video ID: {} not found", videoEngagementDTO.getVideoId());
            return Optional.empty();
        }

        videoEngagementDTO = fromEntity(engagementRepository.save(fromDto(videoEngagementDTO)));

        eventClient.notifyOnVideoEngagementEvent(
                videoEngagementDTO.getUserId(),
                new VideoEngagementEventDTO(
                        videoEngagementDTO.getUserId(),
                        videoEngagementDTO.getVideoId(),
                        videoEngagementDTO.getWatchedTime()
                )
        );

        return Optional.of(videoEngagementDTO);
    }

    // Finds the watch status of a video for a user
    @Override
    public Optional<VideoEngagementDTO> findWatchStatus(String userId, UUID videoId) {
        LOGGER.info("Fetching watch status for video ID: {} and user ID: {}", videoId, userId);
        Optional<UserEngagement> userVideoWatch = engagementRepository.findById(userId, videoId);

        if(userVideoWatch.isEmpty()) {
            LOGGER.info("Watch status not found for video ID: {} and user ID: {}", videoId, userId);
            return Optional.empty();
        }

        return Optional.of(fromEntity(userVideoWatch.get()));
    }

    // Finds the watch history for a user
    @Override
    public List<VideoEngagementDTO> findWatchHistory(String userId) {
        LOGGER.info("Fetching watch history for user ID: {}", userId);
        return engagementRepository
                .findByUser(userId)
                .stream()
                .map(VideoEngagementMapper::fromEntity)
                .toList();
    }
}
