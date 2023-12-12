package com.example.video.service;

import com.example.video.model.LikeVideo;
import com.example.video.producer.MessagePublisher;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.UUID;

@Singleton
public class LikeVideoServiceImpl implements LikeVideoService {
    @Inject
    private MessagePublisher messagePublisher;

    @Override
    public void save(String userId, UUID videoId, boolean likeStatus) {
        LikeVideo likeVideo = new LikeVideo();
        likeVideo.setUserId(userId);
        likeVideo.setVideoId(videoId);
        likeVideo.setLikeStatus(likeStatus);
        messagePublisher.notifyOnLikeDislike(likeVideo.toString());
    }
}
