package com.example.video.producer;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient(id = "vm-client")
public interface KafkaMessagePublisher extends MessagePublisher{

    @Topic("video-creation-events")
    @Override
    void notifyOnNewVideoPosted(String message); // TODO: Add @KafkaKey annotation

    @Topic("video-engagement-events")
    @Override
    void notifyOnVideoWatched(String message);

    @Topic("video-feedback-events")
    @Override
    void notifyOnLikeDislike(String message);
}
