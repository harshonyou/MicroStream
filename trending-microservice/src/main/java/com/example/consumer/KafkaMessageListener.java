// Code Generated Via EGL Template

package com.example.consumer;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;

import com.example.dto.VideoFeedbackEventDTO;

// protected region customImports on begin
// protected region customImports end

@KafkaListener(groupId = "trending-hashtag-microservice-listener", offsetReset = OffsetReset.EARLIEST)
public class KafkaMessageListener {

    // protected region classVariableDeclaration on begin
    // protected region classVariableDeclaration end

    @Topic("video-feedback-events")
    public void handleVideoFeedbackEvents(
            @KafkaKey String key,
            VideoFeedbackEventDTO event) {
        // protected region methodImplementation on begin
        System.out.println("Handling video feedback event for tags: " + event.getTags());
        // Doing nothing, as we have got custom Kafka Stream to handle this
        // com.example.stream.TagAggregatorStream is the custom stream
        // protected region methodImplementation end
    }

}