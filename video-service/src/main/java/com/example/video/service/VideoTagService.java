package com.example.video.service;

import com.example.video.dto.VideoTagDTO;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface VideoTagService {
    void tagVideo(Set<String> tags, UUID videoId); // TODO: not getting used?

    List<VideoTagDTO> search(UUID videoId);
}
