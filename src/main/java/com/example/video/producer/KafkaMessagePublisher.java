package com.example.video.producer;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient(id = "vm-client")
public interface KafkaMessagePublisher extends MessagePublisher{

    @Topic("new-video")
    @Override
    void sendNewVideo(String message);

    @Topic("video-watched")
    @Override
    void sendVideoWatched(String message);
}
