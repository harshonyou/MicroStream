package com.example.video.mapper;

import com.example.video.dto.UserVideoWatchDTO;
import com.example.video.model.UserVideoWatch;

public class UserVideoWatchMapper {
    public static UserVideoWatch fromDto(UserVideoWatchDTO dto) {
        UserVideoWatch uvw = new UserVideoWatch();
        uvw.setUserId(dto.getUserId());
        uvw.setVideoId(dto.getVideoId());
        uvw.setWatchedTime(dto.getWatchedTime());
        return uvw;
    }

    public static UserVideoWatchDTO fromEntity(UserVideoWatch uvw) {
        UserVideoWatchDTO dto = new UserVideoWatchDTO();
        dto.setUserId(uvw.getUserId());
        dto.setVideoId(uvw.getVideoId());
        dto.setWatchedTime(uvw.getWatchedTime());
        return dto;
    }
}
