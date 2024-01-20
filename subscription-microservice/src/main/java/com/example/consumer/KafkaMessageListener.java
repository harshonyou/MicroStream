// Code Generated Via EGL Template

package com.example.consumer;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;

import com.example.dto.VideoCreationEventDTO;
import com.example.dto.VideoEngagementEventDTO;
import com.example.dto.VideoFeedbackEventDTO;

// Define custom imports here
import com.example.dto.*;
import com.example.service.TagService;
import com.example.service.UserService;
import com.example.service.VideoService;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

@KafkaListener(groupId = "subscription-microservice-listener", offsetReset = OffsetReset.EARLIEST)
public class KafkaMessageListener {

    // Define class-level variables here
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaMessageListener.class);

    @Inject
    private UserService userService;

    @Inject
    private VideoService videoService;

    @Inject
    private TagService tagService;


    // Handles the creation of new videos
    @Topic("video-creation-events")
    public void handleVideoCreationEvents(
            @KafkaKey String key,
            VideoCreationEventDTO event) {
        // Custom method implementation goes here
        LOGGER.info("Handling video creation event for video ID: {}", event.getVideoId());
        userService.addUser(event.getUserId(), event.getUserId());
        tagService.addTags(event.getTags());

        VideoDTO video = new VideoDTO();
        video.setId(event.getVideoId());
        video.setTitle(event.getTitle());
        video.setViews(0L);

        Set<TagDTO> tags = event.getTags().stream().map(TagDTO::new).collect(java.util.stream.Collectors.toSet());

        videoService.postVideo(event.getUserId(), video, tags);
    }

    // Handles engagement events like views
    @Topic("video-engagement-events")
    public void handleVideoEngagementEvents(
            @KafkaKey String key,
            VideoEngagementEventDTO event) {
        // Custom method implementation goes here
        LOGGER.info("Handling video engagement event for video ID: {}", event.getVideoId());
        videoService.watchVideo(event.getVideoId(), event.getUserId());
    }

    // Handles feedback events like likes and dislikes
    @Topic("video-feedback-events")
    public void handleVideoFeedbackEvents(
            @KafkaKey String key,
            VideoFeedbackEventDTO event) {
        // Custom method implementation goes here
        LOGGER.info("Handling video feedback event for video ID: {}", event.getVideoId());
        if(event.isLikeStatus()) {
            videoService.likeVideo(event.getVideoId(), event.getUserId());
        } else {
            videoService.dislikeVideo(event.getVideoId(), event.getUserId());
        }
    }
}
