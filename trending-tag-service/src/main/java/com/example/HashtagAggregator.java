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
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
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

    @Inject
    TopTags topTags;

    private final PriorityQueue<Map.Entry<String, Long>> topTagsQueue = new PriorityQueue<>(
            Comparator.comparingLong(Map.Entry::getValue)
    );
    private final Map<String, Long> tagCounts = new HashMap<>();
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

        String stateStoreName = "something-random";
//        StoreBuilder<KeyValueStore<String, Long>> storeBuilder = Stores.keyValueStoreBuilder(
//                Stores.persistentKeyValueStore(stateStoreName),
//                Serdes.String(),
//                Serdes.Long()
//        );
//
//        builder.addStateStore(storeBuilder);


        KStream<String, Tags> stream = builder.stream("tags", Consumed.with(Serdes.String(), tagsSerde));

        KStream<String, Tags> likedStream = stream.filter((key, value) -> value.isLikeStatus());

        KStream<String, String> flatMapStream = likedStream.flatMapValues(Tags::getTags);

        TimeWindows oneHourWindow = TimeWindows.of(Duration.ofMinutes(5));

        KTable<Windowed<String>, Long> windowedHashtagCounts = flatMapStream
                .groupBy((key, hashtag) -> hashtag, Grouped.with(Serdes.String(), Serdes.String()))
                .windowedBy(oneHourWindow)
                .aggregate(
                        () -> 0L,
                        (key, tag, count) -> count + 1,
                        Materialized.<String, Long, WindowStore<Bytes, byte[]>>as(stateStoreName)
                                .withKeySerde(Serdes.String())
                                .withValueSerde(Serdes.Long())
                );

        windowedHashtagCounts.toStream().foreach((key, count) -> updateTopTags(key.key(), count));


//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//        windowedHashtagCounts.toStream()
//                .peek((key, value) -> System.out.println("WINDOWED AGGREGATE | " +
//                        "Current Time: " + LocalDateTime.now(ZoneId.systemDefault()).format(formatter) + ", " +
//                        "Window: " + LocalDateTime.ofInstant(Instant.ofEpochMilli(key.window().start()), ZoneId.systemDefault()).format(formatter) + " to " + LocalDateTime.ofInstant(Instant.ofEpochMilli(key.window().end()), ZoneId.systemDefault()).format(formatter) +
//                        ", Hashtag: " + key.key() +
//                        ", Count: " + value));



//        windowedHashtagCounts.toStream()
//                .foreach((key, value) -> updateTopHashtagsStateStore(key.key(), value));

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//        windowedHashtagCounts.toStream()
//                .peek((key, value) -> System.out.println("WINDOWED AGGREGATE | " +
//                        "Current Time: " + LocalDateTime.now(ZoneId.systemDefault()).format(formatter) + ", " +
//                        "Window: " + LocalDateTime.ofInstant(Instant.ofEpochMilli(key.window().start()), ZoneId.systemDefault()).format(formatter) + " to " + LocalDateTime.ofInstant(Instant.ofEpochMilli(key.window().end()), ZoneId.systemDefault()).format(formatter) +
//                        ", Hashtag: " + key.key() +
//                        ", Count: " + value));

        // Make priority queue of hashtags and their counts and return top 10 hashtags by count

        // Aggregate the number of likes for each hashtag (value)
//        KTable<String, Long> hashtagLikes = flatMapStream
//                .groupBy((key, value) -> value, Grouped.with(Serdes.String(), Serdes.String()))
//                .count();

        // Print the results to the console
//        hashtagLikes.toStream().
//                peek((key, value) -> System.out.println("AGGREGATED | " + "\t" +" Key: " + key + ", Value: " + value));

//        flatMapStream.foreach((key, value) -> System.out.println("STREAMING | " + "\t" +" Key: " + key + ", Value: " + value));

        return stream;
    }

    private synchronized void updateTopTags(String tag, Long count) {
        // Update count in the hash map
        tagCounts.put(tag, count);

        // Add new count to the priority queue
        topTagsQueue.add(new AbstractMap.SimpleEntry<>(tag, count));

        // Remove the smallest count if the queue size exceeds 10
        if (topTagsQueue.size() > 10) {
            topTagsQueue.poll();
        }

        // Optionally, print the current top 10 tags for debugging
        System.out.println("Current Top 10 Tags: " + getTopTags());
    }

    private List<String> getTopTags() {
        return topTagsQueue.stream()
                .sorted(Comparator.comparingLong(Map.Entry<String, Long>::getValue).reversed())
                .map((entry) -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.toList());
    }

    private void updateTopHashtagsStateStore(String hashtag, Long count) {
        topTags.update(hashtag, count);
    }
}
