package com.example.video.service;

import com.example.video.dto.VideoDTO;
import com.example.video.dto.VideoFeedbackDTO;
import com.example.video.dto.VideoFeedbackEventDTO;
import com.example.video.producer.VideoFeedbackEventClient;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Optional;
import java.util.UUID;

@Singleton
public class VideoFeedbackServiceImpl implements VideoFeedbackService {
    private final VideoService videoService;
    private final VideoFeedbackEventClient eventClient;

    @Inject
    private VideoTagService tagService;

    public VideoFeedbackServiceImpl(VideoServiceImpl videoService, VideoFeedbackEventClient eventClient) {
        this.videoService = videoService;
        this.eventClient = eventClient;
    }

    @Override
    public Optional<VideoFeedbackDTO> submitFeedback(String userId, UUID videoId, boolean likeStatus) {
        Optional<VideoDTO> videoDTO = videoService.search(videoId);
        if(videoDTO.isEmpty()) return Optional.empty();

        eventClient.notifyOnLikeDislike(
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
