package com.example.video.service;

import com.example.video.dto.VideoDTO;
import com.example.video.dto.VideoEngagementDTO;
import com.example.video.dto.VideoEngagementEventDTO;
import com.example.video.mapper.VideoEngagementMapper;
import com.example.video.model.UserEngagement;
import com.example.video.producer.VideoEngagementEventClient;
import com.example.video.repository.CassandraVideoEngagementRepository;
import com.example.video.repository.VideoEngagementRepository;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.video.mapper.VideoEngagementMapper.fromDto;
import static com.example.video.mapper.VideoEngagementMapper.fromEntity;

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
