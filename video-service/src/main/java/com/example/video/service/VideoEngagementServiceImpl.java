package com.example.video.service;

import com.datastax.oss.driver.api.core.CqlSession;
import com.example.video.dto.VideoEngagementDTO;
import com.example.video.dto.VideoEngagementEventDTO;
import com.example.video.mapper.VideoEngagementMapper;
import com.example.video.model.UserEngagement;
import com.example.video.producer.VideoEngagementEventClient;
import com.example.video.repository.CassandraVideoEngagementRepository;
import com.example.video.repository.VideoEngagementRepository;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.video.mapper.VideoEngagementMapper.fromDto;
import static com.example.video.mapper.VideoEngagementMapper.fromEntity;

@Singleton
public class VideoEngagementServiceImpl implements VideoEngagementService {
    @Inject
    private CqlSession cqlSession;

    private VideoEngagementRepository userVideoWatchRepository; // TODO: Add @Transactional annotation

    @Inject
    private VideoEngagementEventClient eventClient;

    @PostConstruct
    public void init() {
        userVideoWatchRepository = new CassandraVideoEngagementRepository(cqlSession);
    }

    @Override
    public VideoEngagementDTO save(VideoEngagementDTO videoEngagementDTO) {
        videoEngagementDTO = fromEntity(userVideoWatchRepository.save(fromDto(videoEngagementDTO)));

        eventClient.notifyOnVideoWatched(
                videoEngagementDTO.getUserId(),
                new VideoEngagementEventDTO(
                        videoEngagementDTO.getUserId(),
                        videoEngagementDTO.getVideoId(),
                        videoEngagementDTO.getWatchedTime()
                )
        );

        return videoEngagementDTO;
    }

    @Override
    public Optional<VideoEngagementDTO> findById(String userId, UUID videoId) {
        Optional<UserEngagement> userVideoWatch = userVideoWatchRepository.findById(userId, videoId);
        if(userVideoWatch.isEmpty()) return Optional.empty();
        return Optional.of(fromEntity(userVideoWatch.get()));
    }

    @Override
    public List<VideoEngagementDTO> findByUser(String userId) {
        return userVideoWatchRepository
                .findByUser(userId)
                .stream()
                .map(VideoEngagementMapper::fromEntity)
                .toList();
    }

    @Override
    public List<VideoEngagementDTO> findByVideo(UUID videoId) {
        return userVideoWatchRepository
                .findByVideo(videoId)
                .stream()
                .map(VideoEngagementMapper::fromEntity)
                .toList();
    }
}
