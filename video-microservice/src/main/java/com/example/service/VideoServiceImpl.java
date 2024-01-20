package com.example.service;

import com.example.dto.VideoCreationEventDTO;
import com.example.dto.VideoDTO;
import com.example.model.Video;
import com.example.producer.VideoCreationEventClient;
import com.example.repository.CassandraVideoRepository;
import com.example.repository.VideoRepository;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.mapper.VideoMapper.fromDto;
import static com.example.mapper.VideoMapper.fromEntity;

@Singleton
public class VideoServiceImpl implements VideoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoServiceImpl.class);

    private final VideoRepository videoRepository;
    private final VideoTagService tagService;
    private final VideoCreationEventClient eventClient;

    public VideoServiceImpl(VideoTagService tagService, CassandraVideoRepository videoRepository, VideoCreationEventClient eventClient) {
        this.tagService = tagService;
        this.videoRepository = videoRepository;
        this.eventClient = eventClient;
    }

    // Posts a new video and triggers event
    @Override
    public VideoDTO post(VideoDTO videoDto) {
        LOGGER.info("Posting video: {}", videoDto);
        videoDto = fromEntity(videoRepository.save(fromDto(videoDto)), videoDto.getUserId());

        if(videoDto.getTags() != null) {
            tagService.tagVideo(videoDto.getTags(), videoDto.getVideoId());
        }

        eventClient.notifyOnVideoCreationEvent(
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

    // Searches for a specific video by ID
    @Override
    public Optional<VideoDTO> search(UUID videoId) {
        LOGGER.info("Searching for video with ID: {}", videoId);
        Optional<Video> video = videoRepository.findByVideoId(videoId);

        if(video.isEmpty()) {
            LOGGER.info("Video with ID: {} not found", videoId);
            return Optional.empty();
        }

        return Optional.of(fromEntity(video.get(), video.get().getUserId()));
    }

    // Fetches a specific video by user ID and video ID
    @Override
    public Optional<VideoDTO> fetch(String userId, UUID videoId) {
        LOGGER.info("Fetching video with ID: {} for user ID: {}", videoId, userId);
        Optional<Video> video = videoRepository.findById(userId, videoId);

        if(video.isEmpty()) {
            LOGGER.info("Video with ID: {} not found for user ID: {}", videoId, userId);
            return Optional.empty();
        }

        return Optional.of(fromEntity(video.get(), userId));
    }

    // Retrieves all posts by a specific user
    @Override
    public List<VideoDTO> getUserPosts(String userId) {
        LOGGER.info("Retrieving all posts for user ID: {}", userId);
        return videoRepository
                .findByUser(userId)
                .stream()
                .map(video -> fromEntity(video, userId))
                .toList();
    }

    // Removes a specific video by user ID and video ID
    @Override
    public void remove(String userId, UUID videoId) {
        LOGGER.info("Removing video with ID: {} for user ID: {}", videoId, userId);
        videoRepository.deleteById(userId, videoId);
    }

    // Removes all posts by a specific user
    @Override
    public void removeUserPosts(String userId) {
        LOGGER.info("Removing all posts for user ID: {}", userId);
        videoRepository.deleteByUser(userId);
    }

    // Removes all video posts from the repository
    @Override
    public void removeAllPosts() {
        LOGGER.info("Removing all video posts");
        videoRepository.deleteAll();
    }
}
