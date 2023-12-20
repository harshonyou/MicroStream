package com.example.video.repository;

import com.example.video.model.UserEngagement;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VideoEngagementRepository {
    public static final String TABLE_VIDEO_ENGAGEMENTS = "video_engagements";
    public static final String USER_ID = "user_id";
    public static final String VIDEO_ID = "video_id";
    public static final String WATCHED_TIME = "watched_time";

    UserEngagement save(UserEngagement userEngagement);
    Optional<UserEngagement> findById(String userId, UUID videoId);
    List<UserEngagement> findByUser(String userId);
    List<UserEngagement> findByVideo(UUID videoId);
}
