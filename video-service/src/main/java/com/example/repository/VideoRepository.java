package com.example.repository;

import com.example.model.Video;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VideoRepository {
    public static final String TABLE_VIDEOS = "videos";
    public static final String USER_ID = "user_id";
    public static final String VIDEO_ID = "video_id";
    public static final String VIDEO_TITLE = "title";
    public static final String VIDEO_TAGS = "tags";
    Video save(Video video);
    Optional<Video> findById(String userId, UUID videoId);
    List<Video> findByUser(String userId);
    Optional<Video> findByVideoId(UUID videoId);
    void deleteById(String userId, UUID videoId);
    void deleteByUser(String userId);
    void deleteAll();
}
