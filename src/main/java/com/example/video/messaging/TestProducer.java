package com.example.video.messaging;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient(id = "test_client")
public interface TestProducer {

    @Topic("mob-updates")
    void sendFakeUpdate(String message);
}
