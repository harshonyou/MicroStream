package com.example.consumer;

import com.example.dto.*;
import com.example.mapper.TagMapper;
import com.example.model.Tag;
import com.example.model.Video;
import com.example.repository.UserRepository;
import com.example.repository.VideoRepository;
import com.example.service.TagService;
import com.example.service.UserService;
import com.example.service.VideoService;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;
import org.neo4j.driver.Value;

import java.util.HashSet;
import java.util.Set;

@KafkaListener(groupId = "sm-server", offsetReset = OffsetReset.EARLIEST)
public class KafkaMessageListener {

    @Inject
    private UserService userService;

    @Inject
    private VideoService videoService;

    @Inject
    private TagService tagService;


    @Topic("video-creation-events")
    public void handleVideoCreationEvents(@KafkaKey String key,
            VideoCreationEventDTO event) {
        userService.addUser(event.getUserId(), event.getUserId());
        tagService.addTags(event.getTags());

        VideoDTO video = new VideoDTO();
        video.setId(event.getVideoId());
        video.setTitle(event.getTitle());
        video.setViews(0L);

        Set<TagDTO> tags = event.getTags().stream().map(TagDTO::new).collect(java.util.stream.Collectors.toSet());

        videoService.postVideo(event.getUserId(), video, tags);
    }

    @Topic("video-engagement-events")
    public void handleVideoEngagementEvents(@KafkaKey String key,
                                            VideoEngagementEventDTO event) {
        videoService.watchVideo(event.getVideoId(), event.getUserId());
    }

    @Topic("video-feedback-events")
    public void handleVideoFeedbackEvents(@KafkaKey String key,
                                          VideoFeedbackEventDTO event) {
        videoService.likeVideo(event.getVideoId(), event.getUserId());
    }
}
