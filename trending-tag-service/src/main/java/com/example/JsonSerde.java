package com.example;

import io.micronaut.configuration.kafka.serde.JsonObjectSerde;
import io.micronaut.json.JsonObjectSerializer;
import jakarta.inject.Singleton;

public class JsonSerde<T> extends JsonObjectSerde<T> {
    public JsonSerde(JsonObjectSerializer objectSerializer, Class<T> type) {
        super(objectSerializer, type);
    }
}
