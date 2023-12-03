package com.example.video.consumer;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaListener(groupId = "mmo-server", offsetReset = OffsetReset.EARLIEST)
public class KafkaMessageListener implements MessageListener {

    @Topic("mob-updates")
    public void receiveFakeUpdate(String message) {
        System.out.println("Received message: " + message);
    }
}
