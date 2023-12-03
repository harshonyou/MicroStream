package com.example.video.messaging;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaListener(groupId = "mmo-server", offsetReset = OffsetReset.EARLIEST)
public class TestListener {

    @Topic("mob-updates")
    public void receiveFakeUpdate(String message) {
        System.out.println("Received message: " + message);
    }
}
