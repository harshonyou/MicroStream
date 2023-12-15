//package com.example;
//
//import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder;
//import io.micronaut.context.annotation.Factory;
//import jakarta.inject.Named;
//import jakarta.inject.Singleton;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.streams.KeyValue;
//import org.apache.kafka.streams.StreamsConfig;
//import org.apache.kafka.streams.kstream.*;
//
//import java.time.Duration;
//import java.util.Properties;
//
//@Factory
//public class HashtagAggregator {
//
//    @Singleton
//    @Named("hashtag-aggregator")
//    KStream<String, Boolean> hashtagAggregator(ConfiguredStreamBuilder builder) {
//        Properties props = builder.getConfiguration();
//        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Boolean().getClass());
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, "500");
//
//        KStream<String, Boolean> stream = builder.stream("tags");
//        stream = stream.filter((key, value) -> value);
//        stream.foreach((key, value) -> System.out.println("STREAMS: Key: " + key + ", Value: " + value));
//
//        return stream;
//    }
//}
