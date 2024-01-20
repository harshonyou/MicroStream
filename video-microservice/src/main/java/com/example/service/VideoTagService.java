package com.example.service;

import com.example.dto.VideoTagDTO;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface VideoTagService {
    void tagVideo(Set<String> tags, UUID videoId);

    List<VideoTagDTO> searchVideos(String tag);
}
