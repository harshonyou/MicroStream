// Code Generated Via EGL Template

package com.example.producer;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

import com.example.dto.VideoEngagementEventDTO;


@KafkaClient(id = "video-engagement-events-producer")
public interface VideoEngagementEventClient {
    @Topic("video-engagement-events")
    void notifyOnVideoEngagementEvent(
            @KafkaKey String key,
            VideoEngagementEventDTO event
    );
}
