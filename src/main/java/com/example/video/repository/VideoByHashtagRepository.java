package com.example.video.repository;


import com.example.video.model.VideoByHashtag;

import java.util.List;

public interface VideoByHashtagRepository {
    public static final String TABLE_HASHTAGS = "hashtags";
    public static final String HASHTAG = "hashtag";
    public static final String VIDEO_ID = "video_id";
    VideoByHashtag save(VideoByHashtag videoByHashtag);

    List<VideoByHashtag> findByHashtag(String hashtag);
}
