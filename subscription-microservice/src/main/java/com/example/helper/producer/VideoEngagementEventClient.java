package com.example.helper.producer;

import com.example.dto.VideoCreationEventDTO;
import com.example.dto.VideoEngagementEventDTO;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface VideoEngagementEventClient {
    @Topic("video-engagement-events")
    void send(
            @KafkaKey String key,
            VideoEngagementEventDTO event
    );
}
