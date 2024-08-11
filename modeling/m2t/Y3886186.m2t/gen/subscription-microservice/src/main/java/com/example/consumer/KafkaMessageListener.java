// Code Generated Via EGL Template

package com.example.consumer;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;

import com.example.dto.VideoCreationEventDTO;
import com.example.dto.VideoEngagementEventDTO;
import com.example.dto.VideoFeedbackEventDTO;

// protected region customImports on begin
// protected region customImports end

@KafkaListener(groupId = "subscription-microservice-listener", offsetReset = OffsetReset.EARLIEST)
public class KafkaMessageListener {
	
	// protected region classVariableDeclaration on begin
	// protected region classVariableDeclaration end

	@Topic("video-creation-events")
	public void handleVideoCreationEvents(
		@KafkaKey String key,
		VideoCreationEventDTO event) {
		// protected region methodImplementation on begin
		// protected region methodImplementation end
	}

	@Topic("video-engagement-events")
	public void handleVideoEngagementEvents(
		@KafkaKey String key,
		VideoEngagementEventDTO event) {
		// protected region methodImplementation on begin
		// protected region methodImplementation end
	}

	@Topic("video-feedback-events")
	public void handleVideoFeedbackEvents(
		@KafkaKey String key,
		VideoFeedbackEventDTO event) {
		// protected region methodImplementation on begin
		// protected region methodImplementation end
	}

}