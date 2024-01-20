package com.example.helper.producer;

import com.example.dto.VideoFeedbackEventDTO;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface TagsLikeEventClient {
    @Topic("video-feedback-events")
    void send(
            @KafkaKey String key,
            VideoFeedbackEventDTO tags
    );
}
