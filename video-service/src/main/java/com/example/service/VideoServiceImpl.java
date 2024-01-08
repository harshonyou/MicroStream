package com.example.service;

import com.example.dto.VideoCreationEventDTO;
import com.example.dto.VideoDTO;
import com.example.model.Video;
import com.example.producer.VideoCreationEventClient;
import com.example.repository.CassandraVideoRepository;
import com.example.repository.VideoRepository;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.mapper.VideoMapper.fromDto;
import static com.example.mapper.VideoMapper.fromEntity;

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
    public Optional<VideoDTO> search(UUID videoId) {
        Optional<Video> video = videoRepository.findByVideoId(videoId);
        if(video.isEmpty()) return Optional.empty();
        return Optional.of(fromEntity(video.get(), video.get().getUserId()));
    }

    @Override
    public Optional<VideoDTO> fetch(String userId, UUID videoId) {
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
