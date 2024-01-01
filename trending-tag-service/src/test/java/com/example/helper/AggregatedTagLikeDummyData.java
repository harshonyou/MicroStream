package com.example.helper;

import com.example.model.AggregatedTagLike;
import com.example.repository.AggregatedTagLikeRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AggregatedTagLikeDummyData {

    private static class DummyData {
        String tagName;
        int likeCount;
        long timestamp;

        DummyData(String tagName, int likeCount, long timestamp) {
            this.tagName = tagName;
            this.likeCount = likeCount;
            this.timestamp = timestamp;
        }
    }

    public static void setUpDummyData(AggregatedTagLikeRepository aggregatedTagLikeRepository) {
        long currentTime = System.currentTimeMillis();

        List<DummyData> dummyDataList = Arrays.asList(
                // 2 hours ago
                new DummyData("hashtag1", 10, currentTime - 160 * 60 * 1000),
                new DummyData("hashtag2", 12, currentTime - 160 * 60 * 1000),
                new DummyData("hashtag3", 15, currentTime - 160 * 60 * 1000),
                new DummyData("hashtag4", 7, currentTime - 160 * 60 * 1000),
                new DummyData("hashtag5", 20, currentTime - 160 * 60 * 1000),
                new DummyData("hashtag6", 5, currentTime - 160 * 60 * 1000),
                new DummyData("hashtag7", 13, currentTime - 160 * 60 * 1000),
                new DummyData("hashtag8", 8, currentTime - 160 * 60 * 1000),
                new DummyData("hashtag9", 11, currentTime - 160 * 60 * 1000),

                // 1 hour ago
                new DummyData("hashtag1", 10, currentTime - 60 * 60 * 1000),
                new DummyData("hashtag2", 12, currentTime - 60 * 60 * 1000),
                new DummyData("hashtag3", 15, currentTime - 60 * 60 * 1000),
                new DummyData("hashtag4", 7, currentTime - 60 * 60 * 1000),
                new DummyData("hashtag5", 20, currentTime - 60 * 60 * 1000),
                new DummyData("hashtag6", 5, currentTime - 60 * 60 * 1000),
                new DummyData("hashtag7", 13, currentTime - 60 * 60 * 1000),
                new DummyData("hashtag1", 17, currentTime - 60 * 60 * 1000),
                new DummyData("hashtag8", 8, currentTime - 60 * 60 * 1000),
                new DummyData("hashtag9", 11, currentTime - 60 * 60 * 1000),

                // within the last hour
                new DummyData("hashtag1", 10, currentTime - 10 * 60 * 1000),
                new DummyData("hashtag2", 12, currentTime - 9 * 60 * 1000),
                new DummyData("hashtag3", 15, currentTime - 8 * 60 * 1000),
                new DummyData("hashtag4", 7, currentTime - 7 * 60 * 1000),
                new DummyData("hashtag5", 20, currentTime - 6 * 60 * 1000),
                new DummyData("hashtag6", 5, currentTime - 5 * 60 * 1000),
                new DummyData("hashtag7", 13, currentTime - 4 * 60 * 1000),
                new DummyData("hashtag1", 17, currentTime - 3 * 60 * 1000),
                new DummyData("hashtag8", 8, currentTime - 2 * 60 * 1000),
                new DummyData("hashtag9", 11, currentTime - 60 * 1000),
                new DummyData("hashtag10", 9, currentTime)
        );

        for (DummyData data : dummyDataList) {
            insertDummyData(aggregatedTagLikeRepository, data.tagName, data.likeCount, data.timestamp);
        }
    }

    public static void insertDummyData(AggregatedTagLikeRepository aggregatedTagLikeRepository, String tagName, int likeCount, long timestamp) {
        AggregatedTagLike aggregatedTagLike = new AggregatedTagLike(tagName, (long) likeCount, new java.sql.Timestamp(timestamp));
        AggregatedTagLike savedAggregatedTagLike = aggregatedTagLikeRepository.save(aggregatedTagLike);
        assertEquals(aggregatedTagLike, savedAggregatedTagLike);
    }
}
