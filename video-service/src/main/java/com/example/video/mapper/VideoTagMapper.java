package com.example.video.mapper;

import com.example.video.dto.VideoTagDTO;
import com.example.video.model.VideoTag;

import java.util.UUID;

public class VideoTagMapper {
    public static VideoTag fromDto(VideoTagDTO dto, UUID videoId) {
        VideoTag v = new VideoTag();
        v.setTag(dto.getTag());
        v.setVideoId(videoId);
        return v;
    }

    public static VideoTagDTO fromEntity(VideoTag v) {
        VideoTagDTO dto = new VideoTagDTO();
        dto.setTag(v.getTag());
        return dto;
    }
}
