package com.example.repository;


import com.example.model.VideoTag;

import java.util.List;

public interface VideoTagRepository {
    public static final String TABLE_TAGS = "tags";
    public static final String TAG = "tag";
    public static final String VIDEO_ID = "video_id";
    VideoTag save(VideoTag videoTag);
    List<VideoTag> findByTag(String tag);
}
