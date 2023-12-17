package com.example.events;

import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.WindowStore;

import java.time.Duration;
import java.util.*;


@Factory
public class HashtagAggregator {
    @Inject
    TagsSerde tagsSerde;

    @Singleton
    @Named("hashtag-aggregator")
    KStream<String, Tags> hashtagAggregator(ConfiguredStreamBuilder builder) {
        Properties props = builder.getConfiguration();
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, "0");

        KStream<String, Tags> stream = builder.stream("tags", Consumed.with(Serdes.String(), tagsSerde));

        stream
                .filter((key, value) -> value.isLikeStatus())
                .flatMapValues(Tags::getTags)
                .groupBy((key, hashtag) -> hashtag, Grouped.with(Serdes.String(), Serdes.String()))
                .windowedBy(TimeWindows.of(Duration.ofMinutes(1)).grace(Duration.ofSeconds(10)))
                .aggregate(
                        () -> 0L,
                        (key, tag, count) -> count + 1,
                        Materialized.<String, Long, WindowStore<Bytes, byte[]>>as("something-random")
                                .withKeySerde(Serdes.String())
                                .withValueSerde(Serdes.Long()))
                .suppress(Suppressed.untilWindowCloses(Suppressed.BufferConfig.unbounded()))
                .toStream()
                .transform(() -> new TopNHashtagsTransformer(10, Duration.ofSeconds(35)));

        return stream;
    }
}
