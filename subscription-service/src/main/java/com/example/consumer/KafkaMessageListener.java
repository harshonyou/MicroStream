package com.example.consumer;

import com.example.dto.VideoCreationEventDTO;
import com.example.dto.VideoEngagementEventDTO;
import com.example.dto.VideoFeedbackEventDTO;
import com.example.model.Tag;
import com.example.model.Video;
import com.example.repository.UserRepository;
import com.example.repository.VideoRepository;
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
    private VideoRepository videoRepository;

    @Inject
    private UserRepository userRepository;


    @Topic("video-creation-events")
    public void handleVideoCreationEvents(@KafkaKey String key,
            VideoCreationEventDTO event) {
//        Video video = new Video(event.getVideoId(), event.getTitle(), 0L);
//        Set<Tag> tags = event.getTags().stream().map(Tag::new).collect(HashSet::new, HashSet::add, HashSet::addAll);
//        videoRepository.postVideo(event.getUserId(), video, tags);
        System.out.println("LISTENING | " + "\t" +" Key: " + key + ", Value: " + event);
    }

    @Topic("video-engagement-events")
    public void handleVideoEngagementEvents(@KafkaKey String key,
                                            VideoEngagementEventDTO event) {
//        videoRepository.watchVideo(event.getUserId(), event.getVideoId());
        System.out.println("LISTENING | " + "\t" +" Key: " + key + ", Value: " + event);
    }

    @Topic("video-feedback-events")
    public void handleVideoFeedbackEvents(@KafkaKey String key,
                                          VideoFeedbackEventDTO event) {
//        if (event.isLikeStatus()) {
//            videoRepository.likeVideo(event.getUserId(), event.getVideoId());
//        }
        System.out.println("LISTENING | " + "\t" +" Key: " + key + ", Value: " + event);
    }
}
