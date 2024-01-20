package com.example.model;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false, environments = "no-streams")
class AggregatedTagLikeTest {
    @Test
    public void testConstructor() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        AggregatedTagLike tagLike = new AggregatedTagLike("testTag", 100L, now);

        assertEquals("testTag", tagLike.getTag());
        assertEquals(100L, tagLike.getAggregatedLikes());
        assertEquals(now, tagLike.getMinuteInterval());
    }

    @Test
    public void testSetAndGetTag() {
        AggregatedTagLike tagLike = new AggregatedTagLike(null, null, null);
        tagLike.setTag("exampleTag");

        assertEquals("exampleTag", tagLike.getTag());
    }

    @Test
    public void testSetAndGetAggregatedLikes() {
        AggregatedTagLike tagLike = new AggregatedTagLike(null, null, null);
        tagLike.setAggregatedLikes(200L);

        assertEquals(200L, tagLike.getAggregatedLikes());
    }

    @Test
    public void testSetAndGetMinuteInterval() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        AggregatedTagLike tagLike = new AggregatedTagLike(null, null, null);
        tagLike.setMinuteInterval(now);

        assertEquals(now, tagLike.getMinuteInterval());
    }
}