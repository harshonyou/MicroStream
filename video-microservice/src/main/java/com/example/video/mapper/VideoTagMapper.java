package com.example.video.mapper;

import com.example.video.dto.VideoTagDTO;
import com.example.video.model.VideoTag;

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
