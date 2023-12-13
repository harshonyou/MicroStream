package com.example.video.service;

import com.example.video.dto.VideoTagDTO;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface VideoTagService {
    void save(Set<String> tags, UUID videoId);

    List<VideoTagDTO> findByTag(String tag);
}
