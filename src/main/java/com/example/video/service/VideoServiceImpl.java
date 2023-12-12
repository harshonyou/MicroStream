package com.example.video.service;

import com.datastax.oss.driver.api.core.CqlSession;
import com.example.video.dto.VideoDTO;
import com.example.video.model.Video;
import com.example.video.model.VideoTag;
import com.example.video.producer.MessagePublisher;
import com.example.video.repository.CassandraVideoTagRepository;
import com.example.video.repository.CassandraVideoRepository;
import com.example.video.repository.VideoTagRepository;
import com.example.video.repository.VideoRepository;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.video.mapper.VideoMapper.fromDto;
import static com.example.video.mapper.VideoMapper.fromEntity;

@Singleton
public class VideoServiceImpl implements VideoService {

    @Inject
    private CqlSession cqlSession;
    private VideoRepository videoRepository;
    @Inject
    private VideoTagService videoTagService;
    @Inject
    private MessagePublisher messagePublisher;

    @PostConstruct
    public void init() {
        videoRepository = new CassandraVideoRepository(cqlSession);
    }

//    public VideoServiceImpl(CqlSession cqlSession, MessagePublisher messagePublisher) {
//        this.cqlSession = cqlSession;
//        this.messagePublisher = messagePublisher;
//        videoRepository = new CassandraVideoRepository(cqlSession);
//    }

    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public VideoDTO save(VideoDTO videoDto) {
        videoDto = fromEntity(videoRepository.save(fromDto(videoDto)), videoDto.getUserId());

        if(videoDto.getTags() != null) {
            videoTagService.save(videoDto.getTags(), videoDto.getVideoId());
        }

        messagePublisher.notifyOnNewVideoPosted(videoDto.toString());
        return videoDto;
    }

    @Override
    public Optional<VideoDTO> findById(String userId, UUID videoId) {
        Optional<Video> video = videoRepository.findById(userId, videoId);
        if(video.isEmpty()) return Optional.empty();
        return Optional.of(fromEntity(video.get(), userId));
    }

    @Override
    public List<VideoDTO> findByUser(String userId) {
        return videoRepository
                .findByUser(userId)
                .stream()
                .map(video -> fromEntity(video, userId))
                .toList();
    }

    @Override
    public void deleteById(String userId, UUID videoId) {
        videoRepository.deleteById(userId, videoId);
    }

    @Override
    public void deleteByUser(String userId) {
        videoRepository.deleteByUser(userId);
    }

    @Override
    public void deleteAll() {
        videoRepository.deleteAll();
    }

//    @Scheduled(fixedDelay = "10s")
//    public void sendFakeUpdate() {
//        messagePublisher.sendFakeUpdate("Hello World!");
//    }
}
