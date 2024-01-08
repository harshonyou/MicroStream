package com.example.producer;


import com.example.dto.VideoEngagementEventDTO;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient(id = "video-engagement-events-producer")
public interface VideoEngagementEventClient {
    @Topic("video-engagement-events")
    void notifyOnVideoWatched(
            @KafkaKey String key,
            VideoEngagementEventDTO event
    );
}
