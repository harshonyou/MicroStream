//package com.example;
//
//import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder;
//import io.micronaut.context.annotation.Factory;
//import io.micronaut.context.annotation.Requires;
//import jakarta.inject.Named;
//import jakarta.inject.Singleton;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.streams.StreamsConfig;
//import org.apache.kafka.streams.kstream.Grouped;
//import org.apache.kafka.streams.kstream.KStream;
//import org.apache.kafka.streams.kstream.KTable;
//import org.apache.kafka.streams.kstream.Materialized;
//import org.apache.kafka.streams.kstream.Produced;
//
//import java.util.Arrays;
//import java.util.Properties;
//
//@Factory
//public class WordCountStream {
//
//    @Singleton
//    @Named("word-count")
//    KStream<String, String> wordCountStream(ConfiguredStreamBuilder builder) { // (1)
//        // set default serdes
//        Properties props = builder.getConfiguration();
//        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
//        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, "500");
//
//        KStream<String, String> source = builder.stream("streams-plaintext-input"); // (2)
//
//        KTable<String, Long> groupedByWord = source
//                .flatMapValues(value -> Arrays.asList(value.toLowerCase().split("\\W+")))
//                .groupBy((key, word) -> word, Grouped.with(Serdes.String(), Serdes.String()))
//                //Store the result in a store for lookup later
//                .count(Materialized.as("word-count-store-java")); // (3)
//
//        groupedByWord
//                //convert to stream
//                .toStream()
//                //send to output using specific serdes
//                .to("streams-wordcount-output", Produced.with(Serdes.String(), Serdes.Long())); // (4)
//
//        return source;
//    }
//}