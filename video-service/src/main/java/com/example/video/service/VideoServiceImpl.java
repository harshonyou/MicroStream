package com.example.video.service;

import com.datastax.oss.driver.api.core.CqlSession;
import com.example.video.dto.VideoCreationEventDTO;
import com.example.video.dto.VideoDTO;
import com.example.video.model.Video;
import com.example.video.producer.VideoCreationEventClient;
import com.example.video.repository.CassandraVideoRepository;
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

    private final VideoRepository videoRepository;
    private final VideoTagService tagService;
    private final VideoCreationEventClient eventClient;

    public VideoServiceImpl(VideoTagService tagService, CassandraVideoRepository videoRepository, VideoCreationEventClient eventClient) {
        this.tagService = tagService;
        this.videoRepository = videoRepository;
        this.eventClient = eventClient;
    }


    @Override
    public VideoDTO post(VideoDTO videoDto) {
        videoDto = fromEntity(videoRepository.save(fromDto(videoDto)), videoDto.getUserId());

        if(videoDto.getTags() != null) {
            System.out.println(videoDto.getTags());
            tagService.tagVideo(videoDto.getTags(), videoDto.getVideoId());
        }

        eventClient.notifyOnNewVideoPosted(
                videoDto.getUserId(),
                new VideoCreationEventDTO(
                        videoDto.getUserId(),
                        videoDto.getVideoId(),
                        videoDto.getTitle(),
                        videoDto.getTags()
                )
        );

        return videoDto;
    }

    @Override
    public Optional<VideoDTO> search(String userId, UUID videoId) {
        Optional<Video> video = videoRepository.findById(userId, videoId);
        if(video.isEmpty()) return Optional.empty();
        return Optional.of(fromEntity(video.get(), userId));
    }

    @Override
    public List<VideoDTO> getUserPosts(String userId) {
        return videoRepository
                .findByUser(userId)
                .stream()
                .map(video -> fromEntity(video, userId))
                .toList();
    }

    @Override
    public void remove(String userId, UUID videoId) {
        videoRepository.deleteById(userId, videoId);
    }

    @Override
    public void removeUserPosts(String userId) {
        videoRepository.deleteByUser(userId);
    }

    @Override
    public void removeAllPosts() {
        videoRepository.deleteAll();
    }
}
