package com.example.mapper;

import com.example.dto.VideoEngagementDTO;
import com.example.model.UserEngagement;

public class VideoEngagementMapper {
    public static UserEngagement fromDto(VideoEngagementDTO dto) {
        UserEngagement ue = new UserEngagement();
        ue.setUserId(dto.getUserId());
        ue.setVideoId(dto.getVideoId());
        ue.setWatchedTime(dto.getWatchedTime());
        return ue;
    }

    public static VideoEngagementDTO fromEntity(UserEngagement ue) {
        VideoEngagementDTO dto = new VideoEngagementDTO();
        dto.setUserId(ue.getUserId());
        dto.setVideoId(ue.getVideoId());
        dto.setWatchedTime(ue.getWatchedTime());
        return dto;
    }
}
