// Code Generated Via EGL Template

package com.example.producer;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

import com.example.dto.VideoCreationEventDTO;


@KafkaClient(id = "video-creation-events-producer")
public interface VideoCreationEventClient {
    @Topic("video-creation-events")
    void notifyOnVideoCreationEvent(
            @KafkaKey String key,
            VideoCreationEventDTO event
    );
}
