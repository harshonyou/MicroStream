// Code Generated Via EGL Template

package com.example.producer;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

import com.example.dto.TagEngagementEventDTO;


@KafkaClient(id = "tag-engagement-events-producer")
public interface TagEngagementEventClient {
    @Topic("tag-engagement-events")
    void notifyOnTagEngagementEvent(
            @KafkaKey String key,
            TagEngagementEventDTO event
    );
}
