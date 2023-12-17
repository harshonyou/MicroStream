package com.example.stream;

import com.example.dto.TagsLikeEventDTO;
import com.example.repository.AggregatedTagLikeRepository;
import com.example.serde.TagsLikeEventDTOSerde;
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
import java.util.Properties;

@Factory
public class TagAggregatorStream {

    @Inject
    private AggregatedTagLikeRepository aggregatedTagLikeRepository;
    @Inject
    private TagsLikeEventDTOSerde tagsLikeEventDTOSerde;

    @Singleton
    @Named("tag-aggregator-stream")
    public KStream<String, TagsLikeEventDTO> tagAggregatorStream(ConfiguredStreamBuilder builder) {
        Properties props = new Properties();
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, "0");

        KStream<String, TagsLikeEventDTO> stream = builder.stream("tags-like-event", Consumed.with(Serdes.String(), tagsLikeEventDTOSerde));

        stream
                .filter((tags, likeStatus) -> likeStatus.isLikeStatus() && likeStatus.getTags() != null)
                .flatMapValues(TagsLikeEventDTO::getTags)
                .mapValues(tag -> tag.toLowerCase())
                .groupBy((tags, tag) -> tag, Grouped.with(Serdes.String(), Serdes.String()))
                .windowedBy(TimeWindows.of(Duration.ofMinutes(1)).grace(Duration.ofSeconds(10)))
                .aggregate(
                        () -> 0L,
                        (key, tag, count) -> count + 1,
                        Materialized.<String, Long, WindowStore<Bytes, byte[]>>as("aggregated-tags")
                                .withKeySerde(Serdes.String())
                                .withValueSerde(Serdes.Long()))
                .suppress(Suppressed.untilWindowCloses(Suppressed.BufferConfig.unbounded()))
                .toStream()
                .transform(() -> new TopNTagsTransformer(100, Duration.ofSeconds(35), aggregatedTagLikeRepository));

        return stream;
    }
}
