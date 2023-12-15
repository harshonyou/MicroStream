package com.example;

import io.micronaut.configuration.kafka.serde.JsonObjectSerde;
import io.micronaut.json.JsonObjectSerializer;
import jakarta.inject.Singleton;

@Singleton
public class TagsSerde extends JsonObjectSerde<Tags> {
    public TagsSerde(JsonObjectSerializer objectSerializer) {
        super(objectSerializer, Tags.class);
    }
}
