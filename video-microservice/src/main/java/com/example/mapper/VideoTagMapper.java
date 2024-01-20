package com.example.mapper;

import com.example.dto.VideoTagDTO;
import com.example.model.VideoTag;

public class VideoTagMapper {
    public static VideoTag fromDto(VideoTagDTO dto) {
        VideoTag v = new VideoTag();
        v.setTag(dto.getTag());
        v.setVideoId(dto.getVideoId());
        return v;
    }

    public static VideoTagDTO fromEntity(VideoTag v) {
        VideoTagDTO dto = new VideoTagDTO();
        dto.setTag(v.getTag());
        dto.setVideoId(v.getVideoId());
        return dto;
    }
}
