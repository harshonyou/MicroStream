package com.example.serde;

import com.example.dto.TagsLikeEventDTO;
import io.micronaut.configuration.kafka.serde.JsonObjectSerde;
import io.micronaut.json.JsonObjectSerializer;
import jakarta.inject.Singleton;

@Singleton
public class TagsLikeEventDTOSerde extends JsonObjectSerde<TagsLikeEventDTO> {

    public TagsLikeEventDTOSerde(JsonObjectSerializer objectSerializer) {
        super(objectSerializer, TagsLikeEventDTO.class);
    }

}
