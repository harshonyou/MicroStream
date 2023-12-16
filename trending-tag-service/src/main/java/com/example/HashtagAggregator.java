package com.example;

import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder;
import io.micronaut.context.annotation.Factory;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.WindowStore;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@Factory
public class HashtagAggregator {
    @Inject
    TagsSerde tagsSerde;

    private final PriorityQueue<Map.Entry<String, Long>> topTagsQueue = new PriorityQueue<>(
            Comparator.comparingLong(Map.Entry::getValue)
    );

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
                .transform(() -> new TopNHashtagsTransformer(10, Duration.ofMinutes(1)));

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//        windowedHashtagCounts.toStream()
//                .peek((key, value) -> System.out.println("WINDOWED AGGREGATE | " +
//                        "Current Time: " + LocalDateTime.now(ZoneId.systemDefault()).format(formatter) + ", " +
//                        "Window: " + LocalDateTime.ofInstant(Instant.ofEpochMilli(key.window().start()), ZoneId.systemDefault()).format(formatter) + " to " + LocalDateTime.ofInstant(Instant.ofEpochMilli(key.window().end()), ZoneId.systemDefault()).format(formatter) +
//                        ", Hashtag: " + key.key() +
//                        ", Count: " + value));

        return stream;
    }

    private synchronized void updateTopTags(String tag, Long count) {
        if (topTagsQueue.size() > 10) {
            if (count > topTagsQueue.peek().getValue()) {
                topTagsQueue.add(new AbstractMap.SimpleEntry<>(tag, count));
                topTagsQueue.poll();
            }
        } else {
            topTagsQueue.add(new AbstractMap.SimpleEntry<>(tag, count));
        }

        System.out.println("Current Top 10 Tags: " + getTopTags());
    }

    private List<String> getTopTags() {
        return topTagsQueue.stream()
                .sorted(Comparator.comparingLong(Map.Entry<String, Long>::getValue).reversed())
                .map((entry) -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.toList());
    }

//    @Scheduled(fixedDelay = "10s")
//    public synchronized void purgeTopTags() {
//        System.out.println("Current Top 10 Tags: " + getTopTags());
////        System.out.println("Purging Top 10 Tags");
////        topTagsQueue.clear();
//    }
}
