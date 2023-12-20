package com.example.video.service;

import com.example.video.dto.VideoFeedbackEventDTO;
import com.example.video.producer.VideoFeedbackEventClient;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.UUID;

@Singleton
public class VideoFeedbackServiceImpl implements VideoFeedbackService {
    @Inject
    private VideoFeedbackEventClient eventClient;

    @Override
    public void save(String userId, UUID videoId, boolean likeStatus) {
        eventClient.notifyOnLikeDislike(
                userId,
                new VideoFeedbackEventDTO(
                        userId,
                        videoId,
                        likeStatus
                )
        );
    }
}
