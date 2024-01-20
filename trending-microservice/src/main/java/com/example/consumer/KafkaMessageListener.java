// Code Generated Via EGL Template

package com.example.consumer;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;

import com.example.dto.VideoFeedbackEventDTO;

// Define custom imports here

@KafkaListener(groupId = "trending-hashtag-microservice-listener", offsetReset = OffsetReset.EARLIEST)
public class KafkaMessageListener {

    // Define class-level variables here

    @Topic("video-feedback-events")
    public void handleVideoFeedbackEvents(
            @KafkaKey String key,
            VideoFeedbackEventDTO event) {
        // Custom method implementation goes here
        System.out.println("Handling video feedback event for tags: " + event.getTags());
        // Doing nothing, as we have got custom Kafka Stream to handle this
        // com.example.stream.TagAggregatorStream
    }

}