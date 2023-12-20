package com.example.video.producer;

import com.example.video.dto.VideoFeedbackEventDTO;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient(id = "video-feedback-events-producer")
public interface VideoFeedbackEventClient {
    @Topic("video-feedback-events")
    void notifyOnLikeDislike(
            @KafkaKey String key,
            VideoFeedbackEventDTO event
    );
}
