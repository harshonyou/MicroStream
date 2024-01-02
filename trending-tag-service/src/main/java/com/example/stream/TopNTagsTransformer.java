package com.example.stream;

import com.example.model.AggregatedTagLike;
import com.example.repository.AggregatedTagLikeRepository;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Transformer;
import org.apache.kafka.streams.kstream.Windowed;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.PunctuationType;

import java.time.Duration;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

public class TopNTagsTransformer implements Transformer<Windowed<String>, Long, KeyValue<String, Long>> {

    private final int N;
    private final Duration emitInterval;
    private PriorityQueue<Map.Entry<String, Long>> topNTags;
    private final AggregatedTagLikeRepository aggregatedTagLikeRepository;

    public TopNTagsTransformer(int n, Duration emitInterval, AggregatedTagLikeRepository aggregatedTagLikeRepository) {
        if(n<= 0) throw new IllegalArgumentException("N must be greater than 0");
        this.N = n;
        this.emitInterval = emitInterval;
        this.aggregatedTagLikeRepository = aggregatedTagLikeRepository;
    }


    @Override
    public void init(ProcessorContext context) {
        this.topNTags = new PriorityQueue<>(
                Comparator.comparingLong(Map.Entry::getValue)
        );

        context.schedule(emitInterval, PunctuationType.WALL_CLOCK_TIME, this::punctuate);
    }

    @Override
    public KeyValue<String, Long> transform(Windowed<String> key, Long value) {
        if (topNTags.size() >= N) {
            if (value > topNTags.peek().getValue()) {
                topNTags.add(new AbstractMap.SimpleEntry<>(key.key(), value));
                topNTags.poll();
            }
        } else {
            topNTags.add(new AbstractMap.SimpleEntry<>(key.key(), value));
        }
        return null;
    }

    private void punctuate(long timestamp) {
        System.out.println("Punctuate called");

        PriorityQueue<Map.Entry<String, Long>> currentTopN = new PriorityQueue<>(topNTags);
        while (!currentTopN.isEmpty()) {
            Map.Entry<String, Long> entry = currentTopN.poll();
            AggregatedTagLike aggregatedTagLike = new AggregatedTagLike(entry.getKey(), entry.getValue(), new java.sql.Timestamp(System.currentTimeMillis()));
            aggregatedTagLikeRepository.save(aggregatedTagLike);
        }

        topNTags.clear();
    }

    @Override
    public void close() {

    }
}
