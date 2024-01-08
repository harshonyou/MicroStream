package com.example.producer;

import com.example.dto.VideoCreationEventDTO;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient(id = "video-creation-events-producer")
public interface VideoCreationEventClient {
    @Topic("video-creation-events")
    void notifyOnNewVideoPosted(
            @KafkaKey String key,
            VideoCreationEventDTO event
    );
}
