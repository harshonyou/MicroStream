package com.example.video.mapper;

import com.example.video.dto.VideoDTO;
import com.example.video.model.Video;

public class VideoMapper {
    public static Video fromDto(VideoDTO dto) {
        Video v = new Video();
        v.setUserId(dto.getUserId());
        v.setVideoId(dto.getVideoId());
        v.setTitle(dto.getTitle());
        v.setTags(dto.getTags());
        return v;
    }

    public static VideoDTO fromEntity(Video v, String user) {
        VideoDTO dto = new VideoDTO();
        dto.setUserId(user);
        dto.setVideoId(v.getVideoId());
        dto.setTitle(v.getTitle());
        dto.setTags(v.getTags());
        return dto;
    }
}
