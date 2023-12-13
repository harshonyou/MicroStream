package com.example.video.service;

import com.datastax.oss.driver.api.core.CqlSession;
import com.example.video.dto.VideoTagDTO;
import com.example.video.mapper.VideoTagMapper;
import com.example.video.model.VideoTag;
import com.example.video.repository.CassandraVideoTagRepository;
import com.example.video.repository.VideoTagRepository;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static com.example.video.mapper.VideoTagMapper.fromEntity;


@Singleton
public class VideoTagServiceImpl implements VideoTagService {
    @Inject
    private CqlSession cqlSession;

    private VideoTagRepository videoTagRepository;

    @PostConstruct
    public void init() {
        videoTagRepository = new CassandraVideoTagRepository(cqlSession);
    }

    @Override
    public void save(Set<String> tags, UUID videoId) {
        for (String tag : tags) {
            VideoTag videoTag = new VideoTag();
            videoTag.setTag(tag);
            videoTag.setVideoId(videoId);
            videoTagRepository.save(videoTag);
        }
    }

    @Override
    public List<VideoTagDTO> findByTag(String tag) {
        return videoTagRepository
                .findByTag(tag)
                .stream()
                .map(VideoTagMapper::fromEntity)
                .toList();
    }
}
