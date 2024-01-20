package com.example.dto;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false, environments = "no-streams")
class CurrentHourAggregatedTagLikeDTOTest {
    @Test
    void testObjectInstantiationAndGetters() {
        String expectedTag = "exampleTag";
        Long expectedHourlyLikes = 50L;

        CurrentHourAggregatedTagLikeDTO dto = new CurrentHourAggregatedTagLikeDTO(expectedTag, expectedHourlyLikes);

        assertEquals(expectedTag, dto.getTag());
        assertEquals(expectedHourlyLikes, dto.getHourlyLikes());
    }

    @Test
    void testToString() {
        CurrentHourAggregatedTagLikeDTO dto = new CurrentHourAggregatedTagLikeDTO("exampleTag", 50L);
        String expectedString = "CurrentHourAggregatedTagLikeDTO(tag=exampleTag, hourlyLikes=50)";
        assertEquals(expectedString, dto.toString());
    }

}