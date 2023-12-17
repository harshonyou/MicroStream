package com.example.helper.producer;

import com.example.dto.TagsLikeEventDTO;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface TagsLikeEventClient {
    @Topic("tags-like-event")
    void send(
            @KafkaKey String key,
            TagsLikeEventDTO tags
    );
}
