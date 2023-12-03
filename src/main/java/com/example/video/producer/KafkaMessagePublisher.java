package com.example.video.producer;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient(id = "test_client")
public interface KafkaMessagePublisher extends MessagePublisher{

    @Topic("mob-updates")
    void sendFakeUpdate(String message);
}
