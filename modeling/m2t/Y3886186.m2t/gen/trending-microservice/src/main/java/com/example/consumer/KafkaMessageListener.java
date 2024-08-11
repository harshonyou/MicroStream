// Code Generated Via EGL Template

package com.example.consumer;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;

import com.example.dto.VideoFeedbackEventDTO;

// protected region customImports on begin
// protected region customImports end

@KafkaListener(groupId = "trending-microservice-listener", offsetReset = OffsetReset.EARLIEST)
public class KafkaMessageListener {
	
	// protected region classVariableDeclaration on begin
	// protected region classVariableDeclaration end

	@Topic("video-feedback-events")
	public void handleVideoFeedbackEvents(
		@KafkaKey String key,
		VideoFeedbackEventDTO event) {
		// protected region methodImplementation on begin
		// protected region methodImplementation end
	}

}