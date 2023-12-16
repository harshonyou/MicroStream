package com.example;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Transformer;
import org.apache.kafka.streams.kstream.Windowed;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.PunctuationType;
import org.apache.kafka.streams.state.KeyValueStore;

import java.time.Duration;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class TopNHashtagsTransformer implements Transformer<Windowed<String>, Long, KeyValue<String, Long>> {
        private final Duration emitInterval;
        private final int n;

        private PriorityQueue<Map.Entry<String, Long>> topNHashtags;
//        private KeyValueStore<String, Long> stateStore;

        public TopNHashtagsTransformer(int n, Duration emitInterval) {
            if(n <= 0) throw new IllegalArgumentException("N must be greater than 0");
            this.n = n;
            this.emitInterval = emitInterval;
        }

        @Override
        public void init(ProcessorContext context) {
//            this.stateStore = context.getStateStore("hashtag-counts");
            this.topNHashtags = new PriorityQueue<>(
                    Comparator.comparingLong(Map.Entry::getValue)
            );

            context.schedule(emitInterval, PunctuationType.WALL_CLOCK_TIME, this::punctuate);
        }

    @Override
    public KeyValue<String, Long> transform(Windowed<String> key, Long value) {
//        System.out.println("TopNHashtagsTransformer | " + key.key() + " : " + value);
        if (topNHashtags.size() > n) {
            if (value > topNHashtags.peek().getValue()) {
                topNHashtags.add(new AbstractMap.SimpleEntry<>(key.key(), value));
                topNHashtags.poll();
            }
        } else {
            topNHashtags.add(new AbstractMap.SimpleEntry<>(key.key(), value));
        }
        return null;
    }

    private void punctuate(long timestamp) {
        // Emit the top N hashtags at each punctuator call
//        PriorityQueue<Map.Entry<String, Long>> currentTopN = new PriorityQueue<>(topNHashtags);
//        while (!currentTopN.isEmpty()) {
//            KeyValue<String, Long> entry = currentTopN.poll();
//            uploadToPostgres(entry.key, entry.value);
//        }
        System.out.println("TopNHashtagsTransformer | Top " + n + " hashtags:" + topNHashtags.stream()
                .sorted(Comparator.comparingLong(Map.Entry<String, Long>::getValue).reversed())
                .map((entry) -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.toList()));

        // Clear the topNHashtags for the next window
        topNHashtags.clear();
    }

    @Override
    public void close() {

    }
}
