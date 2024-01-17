// Code Generated Via EGL Template

package com.example.producer;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

import com.example.dto.VideoFeedbackEventDTO;


@KafkaClient(id = "video-feedback-events-producer")
public interface VideoFeedbackEventClient {
    @Topic("video-feedback-events")
    void notifyOnVideoFeedbackEvent(
            @KafkaKey String key,
            VideoFeedbackEventDTO event
    );
}
