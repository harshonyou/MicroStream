package com.example.stream;

import com.example.dto.VideoFeedbackEventDTO;
import com.example.repository.AggregatedTagLikeRepository;
import com.example.serde.VideoFeedbackEventDTOSerde;
import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Property;
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
import java.util.Properties;

@Factory
public class TagAggregatorStream {

    @Inject
    private AggregatedTagLikeRepository aggregatedTagLikeRepository;
    @Inject
    private VideoFeedbackEventDTOSerde tagsLikeEventDTOSerde;

    @Property(name = "streams.aggregation.n")
    private Integer aggregationN;

    @Singleton
    @Named("tag-aggregator-stream")
    public KStream<String, VideoFeedbackEventDTO> tagAggregatorStream(ConfiguredStreamBuilder builder) {
        Properties props = new Properties();
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, "0");

        // Creating a Kafka Stream from the 'video-feedback-events' topic
        KStream<String, VideoFeedbackEventDTO> stream = builder.stream("video-feedback-events", Consumed.with(Serdes.String(), tagsLikeEventDTOSerde));

        stream
                // Filter events where the like status is true and tags are not null
                .filter((tags, likeStatus) -> likeStatus.isLikeStatus() && likeStatus.getTags() != null)
                // Flatten the list of tags to individual tags
                .flatMapValues(VideoFeedbackEventDTO::getTags)
                // Convert all tags to lowercase
                .mapValues(tag -> tag.toLowerCase())
                // Group by tag name
                .groupBy((tags, tag) -> tag, Grouped.with(Serdes.String(), Serdes.String()))
                // Create a time window of 1 minute with a 10-second grace period
                .windowedBy(TimeWindows.of(Duration.ofMinutes(1)).grace(Duration.ofSeconds(10)))
                // Aggregate the count of each tag in the given time window
                .aggregate(
                        () -> 0L,
                        (key, tag, count) -> count + 1,
                        Materialized.<String, Long, WindowStore<Bytes, byte[]>>as("aggregated-tags")
                                .withKeySerde(Serdes.String())
                                .withValueSerde(Serdes.Long()))
                // Suppress intermediate results and emit final results per window
                .suppress(Suppressed.untilWindowCloses(Suppressed.BufferConfig.unbounded()))
                // Convert aggregated results to a stream
                .toStream()
                // Transform the stream to maintain only the top N tags
                .transform(() -> new TopNTagsTransformer(aggregationN, Duration.ofSeconds(35), aggregatedTagLikeRepository));

        return stream;
    }
}
