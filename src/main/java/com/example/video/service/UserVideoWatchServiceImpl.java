package com.example.video.service;

import com.datastax.oss.driver.api.core.CqlSession;
import com.example.video.dto.UserVideoWatchDTO;
import com.example.video.mapper.UserVideoWatchMapper;
import com.example.video.model.UserVideoWatch;
import com.example.video.producer.MessagePublisher;
import com.example.video.repository.CassandraUserVideoWatchRepository;
import com.example.video.repository.UserVideoWatchRepository;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.video.mapper.UserVideoWatchMapper.fromDto;
import static com.example.video.mapper.UserVideoWatchMapper.fromEntity;

@Singleton
public class UserVideoWatchServiceImpl implements UserVideoWatchService{
    @Inject
    private CqlSession cqlSession;

    private UserVideoWatchRepository userVideoWatchRepository;

    @Inject
    private MessagePublisher messagePublisher;

    @PostConstruct
    public void init() {
        userVideoWatchRepository = new CassandraUserVideoWatchRepository(cqlSession);
    }



    @Override
    public UserVideoWatchDTO save(UserVideoWatchDTO userVideoWatchDto) {
        userVideoWatchDto = fromEntity(userVideoWatchRepository.save(fromDto(userVideoWatchDto)));
        messagePublisher.sendVideoWatched(userVideoWatchDto.toString());
        return userVideoWatchDto;
    }

    @Override
    public Optional<UserVideoWatchDTO> findById(String userId, UUID videoId) {
        Optional<UserVideoWatch> userVideoWatch = userVideoWatchRepository.findById(userId, videoId);
        if(userVideoWatch.isEmpty()) return Optional.empty();
        return Optional.of(fromEntity(userVideoWatch.get()));
    }

    @Override
    public List<UserVideoWatchDTO> findByUser(String userId) {
        return userVideoWatchRepository
                .findByUser(userId)
                .stream()
                .map(UserVideoWatchMapper::fromEntity)
                .toList();
    }

    @Override
    public List<UserVideoWatchDTO> findByVideo(UUID videoId) {
        return userVideoWatchRepository
                .findByVideo(videoId)
                .stream()
                .map(UserVideoWatchMapper::fromEntity)
                .toList();
    }
}
