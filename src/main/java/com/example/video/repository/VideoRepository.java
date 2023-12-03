package com.example.video.repository;

import com.example.video.dto.VideoDTO;
import com.example.video.model.Video;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VideoRepository {
    public static final String TABLE_VIDEOS = "videos";
    public static final String USER_ID = "user_id";
    public static final String VIDEO_ID = "item_id";
    public static final String VIDEO_TITLE = "title";
    public static final String VIDEO_WATCHED = "watched";

    Video save(Video video);
    Optional<Video> findById(String userId, UUID videoId);
    List<Video> findByUser(String userId);
    void deleteById(String userId, UUID videoId);
    void deleteByUser(String userId);
    void deleteAll();
}
