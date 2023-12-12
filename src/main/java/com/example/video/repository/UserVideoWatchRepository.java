package com.example.video.repository;

import com.example.video.model.UserVideoWatch;
import com.example.video.model.Video;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserVideoWatchRepository {
    public static final String TABLE_WATCHED_VIDEOS = "watched_videos";
    public static final String USER_ID = "user_id";
    public static final String VIDEO_ID = "video_id";
    public static final String WATCHED_TIME = "watched_time";

    UserVideoWatch save(UserVideoWatch userVideoWatch);
    Optional<UserVideoWatch> findById(String userId, UUID videoId);
    List<UserVideoWatch> findByUser(String userId);
    List<UserVideoWatch> findByVideo(UUID videoId);
}
