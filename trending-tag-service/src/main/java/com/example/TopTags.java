package com.example;

import jakarta.inject.Singleton;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class TopTags {
    private final int maxSize = 10;
    private final PriorityQueue<TagCount> topTags;
    private final Map<String, Long> tagCounts;

    public TopTags() {
        topTags = new PriorityQueue<>(maxSize, Comparator.comparingLong(TagCount::getCount).reversed());
        tagCounts = new ConcurrentHashMap<>();
    }
    public synchronized void  update(String tag, long count) {
        tagCounts.put(tag, count);

        // Add the hashtag to the PriorityQueue
        topTags.offer(new TagCount(tag, count));

        // Remove the least frequent hashtag if the size exceeds maxSize
        if (topTags.size() > maxSize) {
            TagCount removed = topTags.poll();
            tagCounts.remove(removed.getHashtag());
        }
    }

    public Map<String, Long> getTopHashtags() {
        return tagCounts;
    }
}
