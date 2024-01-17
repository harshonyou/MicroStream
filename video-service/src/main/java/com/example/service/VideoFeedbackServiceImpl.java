package com.example.service;

import com.example.dto.VideoDTO;
import com.example.dto.VideoFeedbackDTO;
import com.example.producer.VideoFeedbackEventClient;
import com.example.dto.VideoFeedbackEventDTO;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.UUID;

@Singleton
public class VideoFeedbackServiceImpl implements VideoFeedbackService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoFeedbackServiceImpl.class);

    private final VideoService videoService;
    private final VideoFeedbackEventClient eventClient;

    @Inject
    private VideoTagService tagService;

    public VideoFeedbackServiceImpl(VideoServiceImpl videoService, VideoFeedbackEventClient eventClient) {
        this.videoService = videoService;
        this.eventClient = eventClient;
    }

    // Submits feedback (like/dislike) for a video
    @Override
    public Optional<VideoFeedbackDTO> submitFeedback(String userId, UUID videoId, boolean likeStatus) {
        LOGGER.info("Submitting feedback for video ID: {} by user ID: {}", videoId, userId);
        Optional<VideoDTO> videoDTO = videoService.search(videoId);

        if(videoDTO.isEmpty()) {
            LOGGER.info("Video ID: {} not found", videoId);
            return Optional.empty();
        }

        eventClient.notifyOnVideoFeedbackEvent(
                userId,
                new VideoFeedbackEventDTO(
                        userId,
                        videoId,
                        videoDTO.get().getTags(),
                        likeStatus
                )
        );

        return Optional.of(new VideoFeedbackDTO(userId, videoId, likeStatus));
    }
}
