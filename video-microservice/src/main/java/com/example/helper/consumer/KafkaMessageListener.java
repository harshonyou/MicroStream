package com.example.helper.consumer;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaListener(groupId = "vm-server", offsetReset = OffsetReset.EARLIEST)
public class KafkaMessageListener implements MessageListener {
    @Topic("video-creation-events")
    @Override
    public void handleVideoCreationEvents(String message) {
        System.out.println("Received message: " + message);
    }

    @Topic("video-engagement-events")
    @Override
    public void handleVideoEngagementEvents(String message) {
        System.out.println("Received message: " + message);
    }
    @Topic("video-feedback-events")
    @Override
    public void handleVideoFeedbackEvents(String message) {
        System.out.println("Received message: " + message);
    }
}
