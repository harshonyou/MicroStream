package com.example.video.repository;


import com.example.video.model.VideoTag;

import java.util.List;
import java.util.UUID;

public interface VideoTagRepository {
    public static final String TABLE_TAGS = "tags";
    public static final String TAG = "tag";
    public static final String VIDEO_ID = "video_id";
    VideoTag save(VideoTag videoTag);
    List<VideoTag> findTagsByVideoId(UUID videoId);
}
