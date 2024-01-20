package com.example.stream;

import com.example.controller.AggregatedTagLikeController;
import com.example.model.AggregatedTagLike;
import com.example.repository.AggregatedTagLikeRepository;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Transformer;
import org.apache.kafka.streams.kstream.Windowed;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.PunctuationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

public class TopNTagsTransformer implements Transformer<Windowed<String>, Long, KeyValue<String, Long>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopNTagsTransformer.class);

    private final int N; // The number of top tags to maintain
    private final Duration emitInterval;
    private PriorityQueue<Map.Entry<String, Long>> topNTags; // Priority queue to maintain top N tags
    private final AggregatedTagLikeRepository aggregatedTagLikeRepository;

    public TopNTagsTransformer(int n, Duration emitInterval, AggregatedTagLikeRepository aggregatedTagLikeRepository) {
        if(n<= 0) throw new IllegalArgumentException("N must be greater than 0");
        this.N = n;
        this.emitInterval = emitInterval;
        this.aggregatedTagLikeRepository = aggregatedTagLikeRepository;
    }


    @Override
    public void init(ProcessorContext context) {
        // Initialize priority queue with a comparator based on the value (count)
        this.topNTags = new PriorityQueue<>(
                Comparator.comparingLong(Map.Entry::getValue)
        );

        // Schedule the punctuation based on the WALL_CLOCK_TIME and emitInterval
        context.schedule(emitInterval, PunctuationType.WALL_CLOCK_TIME, this::punctuate);
    }

    @Override
    public KeyValue<String, Long> transform(Windowed<String> key, Long value) {
        // Process each tag and its count, maintaining only the top N tags
        if (topNTags.size() >= N) {
            if (value > topNTags.peek().getValue()) {
                topNTags.add(new AbstractMap.SimpleEntry<>(key.key(), value));
                topNTags.poll(); // Remove the smallest element
            }
        } else {
            topNTags.add(new AbstractMap.SimpleEntry<>(key.key(), value));
        }
        return null; // No forwarding of data during transformation
    }

    private void punctuate(long timestamp) {
        // Punctuation method to periodically emit the top N tags
        // Punctuate is not called for a given window unless there is a new record for the very next window (i.e. the window hasn't closed yet)
        LOGGER.info("Emitting top {} tags at {}", N, timestamp);

        PriorityQueue<Map.Entry<String, Long>> currentTopN = new PriorityQueue<>(topNTags);
        while (!currentTopN.isEmpty()) {
            Map.Entry<String, Long> entry = currentTopN.poll();
            AggregatedTagLike aggregatedTagLike = new AggregatedTagLike(entry.getKey(), entry.getValue(), new java.sql.Timestamp(System.currentTimeMillis()));
            aggregatedTagLikeRepository.save(aggregatedTagLike); // Save each top tag to the repository
        }

        topNTags.clear(); // Clear the queue for the next interval
    }

    @Override
    public void close() {

    }
}
