package com.example.mapper;

import com.example.dto.VideoDTO;
import com.example.model.Video;

public class VideoMapper {
    public static Video fromDTO(VideoDTO videoDTO) {
        Video v = new Video();
        v.setId(videoDTO.getId());
        v.setTitle(videoDTO.getTitle());
        v.setViews(videoDTO.getViews());
        return v;
    }

    public static VideoDTO fromEntity(Video video) {
        VideoDTO dto = new VideoDTO();
        dto.setId(video.getId());
        dto.setTitle(video.getTitle());
        dto.setViews(video.getViews());
        return dto;
    }
}
