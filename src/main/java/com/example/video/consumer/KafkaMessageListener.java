package com.example.video.consumer;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaListener(groupId = "vm-server", offsetReset = OffsetReset.EARLIEST)
public class KafkaMessageListener implements MessageListener {
    @Topic("new-video")
    @Override
    public void receiveNewVideo(String message) {
        System.out.println("Received message: " + message);
    }

    @Topic("video-watched")
    @Override
    public void receiveVideoWatched(String message) {
        System.out.println("Received message: " + message);
    }
}
