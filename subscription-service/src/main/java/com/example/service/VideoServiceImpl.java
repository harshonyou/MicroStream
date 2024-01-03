package com.example.service;

import com.example.dto.TagDTO;
import com.example.dto.VideoDTO;
import com.example.mapper.TagMapper;
import com.example.model.Tag;
import com.example.repository.Neo4jVideoRepository;
import com.example.repository.VideoRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Set;
import java.util.UUID;

import static com.example.mapper.VideoMapper.fromDTO;

@Singleton
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;

    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public void postVideo(String userId, VideoDTO video, Set<TagDTO> tags) {
        Set<Tag> t = tags.stream().map(TagMapper::fromDTO).collect(java.util.stream.Collectors.toSet());
        videoRepository.postVideo(userId, fromDTO(video), t);
    }

    @Override
    public void likeVideo(UUID videoId, String userId) {
        videoRepository.likeVideo(videoId, userId);
    }

    @Override
    public void watchVideo(UUID videoId, String userId) {
        videoRepository.watchVideo(videoId, userId);
        videoRepository.incrementVideoViews(videoId);
    }
}
