package com.example.service;

import com.example.dto.TagDTO;
import com.example.dto.VideoDTO;
import com.example.mapper.TagMapper;
import com.example.model.Tag;
import com.example.repository.VideoRepository;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.UUID;

import static com.example.mapper.VideoMapper.fromDTO;

@Singleton
public class VideoServiceImpl implements VideoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoServiceImpl.class);

    private final VideoRepository videoRepository;

    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public void postVideo(String userId, VideoDTO video, Set<TagDTO> tags) {
        LOGGER.info("Posting video for user ID: {}", userId);
        Set<Tag> t = tags.stream().map(TagMapper::fromDTO).collect(java.util.stream.Collectors.toSet());
        videoRepository.postVideo(userId, fromDTO(video), t);
    }

    @Override
    public void likeVideo(UUID videoId, String userId) {
        LOGGER.info("Liking video with ID: {} by user ID: {}", videoId, userId);
        videoRepository.likeVideo(videoId, userId);
    }

    @Override
    public void dislikeVideo(UUID videoId, String userId) {
        LOGGER.info("Disliking video with ID: {} by user ID: {}", videoId, userId);
        boolean isLiked = videoRepository.isLiked(videoId, userId);

        if (isLiked) {
            videoRepository.dislikeVideo(videoId, userId);
        }
    }

    @Override
    public void watchVideo(UUID videoId, String userId) {
        LOGGER.info("User ID: {} is watching video ID: {}", userId, videoId);
        videoRepository.watchVideo(videoId, userId);
        videoRepository.incrementVideoViews(videoId);
    }
}
