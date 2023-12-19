package com.example.helper.producer;

import com.example.dto.VideoCreationEventDTO;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface VideoCreationEventClient {
    @Topic("video-creation-events")
    void send(
            @KafkaKey String key,
            VideoCreationEventDTO event
    );
}
