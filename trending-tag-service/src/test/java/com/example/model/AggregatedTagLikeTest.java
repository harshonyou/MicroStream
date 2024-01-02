package com.example.model;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false, environments = "no-streams")
class AggregatedTagLikeTest {

    @Test
    public void testGettersAndSetters() {
        AggregatedTagLike tagLike = new AggregatedTagLike("java", 100L, new Timestamp(System.currentTimeMillis()));
        assertEquals("java", tagLike.getTag());
        assertEquals(Long.valueOf(100), tagLike.getAggregatedLikes());
        assertNotNull(tagLike.getMinuteInterval());
    }

    @Test
    public void testAllArgsConstructor() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        AggregatedTagLike tagLike = new AggregatedTagLike("java", 100L, now);
        assertEquals("java", tagLike.getTag());
        assertEquals(Long.valueOf(100), tagLike.getAggregatedLikes());
        assertEquals(now, tagLike.getMinuteInterval());
    }


    @Test
    public void testNullValues() {
        AggregatedTagLike tagLike = new AggregatedTagLike(null, null, null);
        assertNull(tagLike.getTag());
        assertNull(tagLike.getAggregatedLikes());
        assertNull(tagLike.getMinuteInterval());
    }

    @Test
    public void testEqualsAndHashCode() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        AggregatedTagLike tagLike1 = new AggregatedTagLike("java", 100L, now);
        AggregatedTagLike tagLike2 = new AggregatedTagLike("java", 100L, now);

        assertEquals(tagLike1, tagLike2);
        assertEquals(tagLike1.hashCode(), tagLike2.hashCode());
    }

    @Test
    public void testToString() {
        AggregatedTagLike tagLike = new AggregatedTagLike("java", 100L, new Timestamp(System.currentTimeMillis()));
        String expected = "AggregatedTagLike(tag=java, aggregatedLikes=100, minuteInterval=" + tagLike.getMinuteInterval() + ")";
        assertEquals(expected, tagLike.toString());
    }

    @Test
    public void testSetters() {
        AggregatedTagLike tagLike = new AggregatedTagLike(null, null, null);

        tagLike.setTag("java");
        assertEquals("java", tagLike.getTag());

        tagLike.setAggregatedLikes(100L);
        assertEquals(Long.valueOf(100), tagLike.getAggregatedLikes());

        Timestamp now = new Timestamp(System.currentTimeMillis());
        tagLike.setMinuteInterval(now);
        assertEquals(now, tagLike.getMinuteInterval());
    }

    @Test
    public void testCanEqual() {
        AggregatedTagLike tagLike = new AggregatedTagLike("java", 100L, new Timestamp(System.currentTimeMillis()));
        assertTrue(tagLike.canEqual(new AggregatedTagLike("java", 100L, new Timestamp(System.currentTimeMillis()))));
        assertFalse(tagLike.canEqual(new Object()));
    }
}