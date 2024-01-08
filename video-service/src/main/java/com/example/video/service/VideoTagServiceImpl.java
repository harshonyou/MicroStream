package com.example.video.service;

import com.example.video.dto.VideoTagDTO;
import com.example.video.mapper.VideoTagMapper;
import com.example.video.model.VideoTag;
import com.example.video.repository.CassandraVideoTagRepository;
import com.example.video.repository.VideoTagRepository;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Singleton
public class VideoTagServiceImpl implements VideoTagService {

    private final VideoTagRepository tagRepository;

    public VideoTagServiceImpl(CassandraVideoTagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public void tagVideo(Set<String> tags, UUID videoId) {
        for (String tag : tags) {
            VideoTag videoTag = new VideoTag();
            videoTag.setVideoId(videoId);
            videoTag.setTag(tag);
            tagRepository.save(videoTag);
        }
    }

    @Override
    public List<VideoTagDTO> searchVideos(String tag) {
        return tagRepository
            .findByTag(tag)
            .stream()
            .map(VideoTagMapper::fromEntity)
            .toList();
    }
}
