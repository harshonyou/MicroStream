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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.mapper.VideoEngagementMapper.fromDto;
import static com.example.mapper.VideoEngagementMapper.fromEntity;

@Singleton
public class VideoEngagementServiceImpl implements VideoEngagementService {
    private final VideoService videoService;

    private final VideoEngagementRepository engagementRepository; // TODO: Add @Transactional annotation
    private final VideoEngagementEventClient eventClient;

    public VideoEngagementServiceImpl(VideoServiceImpl videoService,CassandraVideoEngagementRepository engagementRepository, VideoEngagementEventClient eventClient) {
        this.engagementRepository = engagementRepository;
        this.videoService = videoService;
        this.eventClient = eventClient;
    }

    @Override
    public Optional<VideoEngagementDTO> markVideoWatched(VideoEngagementDTO videoEngagementDTO) {
        Optional<VideoDTO> videoDTO = videoService.search(videoEngagementDTO.getVideoId());
        if(videoDTO.isEmpty()) {
            return Optional.empty();
        }

        videoEngagementDTO = fromEntity(engagementRepository.save(fromDto(videoEngagementDTO)));

        eventClient.notifyOnVideoWatched(
                videoEngagementDTO.getUserId(),
                new VideoEngagementEventDTO(
                        videoEngagementDTO.getUserId(),
                        videoEngagementDTO.getVideoId(),
                        videoEngagementDTO.getWatchedTime()
                )
        );

        return Optional.of(videoEngagementDTO);
    }

    @Override
    public Optional<VideoEngagementDTO> findWatchStatus(String userId, UUID videoId) {
        Optional<UserEngagement> userVideoWatch = engagementRepository.findById(userId, videoId);
        if(userVideoWatch.isEmpty()) return Optional.empty();
        return Optional.of(fromEntity(userVideoWatch.get()));
    }

    @Override
    public List<VideoEngagementDTO> findWatchHistory(String userId) {
        return engagementRepository
                .findByUser(userId)
                .stream()
                .map(VideoEngagementMapper::fromEntity)
                .toList();
    }
}
