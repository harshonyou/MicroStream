package com.example.service;

import com.example.dto.VideoTagDTO;
import com.example.mapper.VideoTagMapper;
import com.example.model.VideoTag;
import com.example.repository.VideoTagRepository;
import com.example.repository.CassandraVideoTagRepository;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Singleton
public class VideoTagServiceImpl implements VideoTagService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoTagServiceImpl.class);

    private final VideoTagRepository tagRepository;

    public VideoTagServiceImpl(CassandraVideoTagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    // Tags a video with a set of tags
    @Override
    public void tagVideo(Set<String> tags, UUID videoId) {
        LOGGER.info("Tagging video ID: {} with tags: {}", videoId, tags);
        for (String tag : tags) {
            VideoTag videoTag = new VideoTag();
            videoTag.setVideoId(videoId);
            videoTag.setTag(tag);
            tagRepository.save(videoTag);
        }
    }

    // Searches videos by a specific tag
    @Override
    public List<VideoTagDTO> searchVideos(String tag) {
        LOGGER.info("Searching videos for tag: {}", tag);
        return tagRepository
            .findByTag(tag)
            .stream()
            .map(VideoTagMapper::fromEntity)
            .toList();
    }
}
