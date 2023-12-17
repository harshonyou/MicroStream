package com.example.events;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface TagClient {

    @Topic("tags")
    void send(
            @KafkaKey String key,
            Tags tags
    );
}
