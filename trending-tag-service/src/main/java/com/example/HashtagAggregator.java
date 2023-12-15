package com.example;

import io.micronaut.configuration.kafka.serde.JsonObjectSerde;
import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder;
import io.micronaut.context.annotation.Factory;
import io.micronaut.json.JsonObjectSerializer;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;

import java.time.Duration;
import java.util.Properties;


@Factory
public class HashtagAggregator {
    @Inject
    TagsSerde tagsSerde;
//    JsonObjectSerializer objectSerializer;
    @Singleton
    @Named("hashtag-aggregator")
    KStream<String, Tags> hashtagAggregator(ConfiguredStreamBuilder builder) {
        Properties props = builder.getConfiguration();
//        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
//        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, TagsSerde.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, "0");

//        JsonSerde<Tags> tagsSerde = new JsonSerde<>(objectSerializer, Tags.class);
//        JsonObjectSerde<Tags> tagsSerde = new JsonObjectSerde<>(objectSerializer, Tags.class);

        KStream<String, Tags> stream = builder.stream("tags", Consumed.with(Serdes.String(), tagsSerde));
        stream.foreach((key, value) -> System.out.println("STREAMING | " + "\t" +" Key: " + key + ", Value: " + value));

        return stream;
    }
}
