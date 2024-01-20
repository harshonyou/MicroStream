package com.example.dto;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false, environments = "no-streams")
class PastIntervalAggregatedTagLikeDTOTest {
    @Test
    void testObjectInstantiationAndGetters() {
        String expectedTag = "exampleTag";
        Long expectedIntervalLikes = 75L;

        PastIntervalAggregatedTagLikeDTO dto = new PastIntervalAggregatedTagLikeDTO(expectedTag, expectedIntervalLikes);

        assertEquals(expectedTag, dto.getTag());
        assertEquals(expectedIntervalLikes, dto.getIntervalLikes());
    }

    @Test
    void testToString() {
        PastIntervalAggregatedTagLikeDTO dto = new PastIntervalAggregatedTagLikeDTO("exampleTag", 75L);
        String expectedString = "PastIntervalAggregatedTagLikeDTO(tag=exampleTag, intervalLikes=75)";
        assertEquals(expectedString, dto.toString());
    }
}