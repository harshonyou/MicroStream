package com.example.video.mapper;

import com.example.video.dto.VideoEngagementDTO;
import com.example.video.model.UserEngagement;

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
