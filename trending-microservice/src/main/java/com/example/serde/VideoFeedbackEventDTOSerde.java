package com.example.serde;

import com.example.dto.VideoFeedbackEventDTO;
import io.micronaut.configuration.kafka.serde.JsonObjectSerde;
import io.micronaut.json.JsonObjectSerializer;
import jakarta.inject.Singleton;

@Singleton
public class VideoFeedbackEventDTOSerde extends JsonObjectSerde<VideoFeedbackEventDTO> {

    public VideoFeedbackEventDTOSerde(JsonObjectSerializer objectSerializer) {
        super(objectSerializer, VideoFeedbackEventDTO.class);
    }

}
