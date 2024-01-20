package com.example.repository;

import com.example.dto.CurrentHourAggregatedTagLikeDTO;
import com.example.dto.PastIntervalAggregatedTagLikeDTO;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.helper.AggregatedTagLikeDummyData.setUpDummyData;
import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false, environments = "no-streams")
class AggregatedTagLikeRepositoryTest {
    @Inject
    AggregatedTagLikeRepository aggregatedTagLikeRepository;

    @BeforeEach
    public void setUp() {
        aggregatedTagLikeRepository.deleteAll();
        assertEquals(0, aggregatedTagLikeRepository.count());
        setUpDummyData(aggregatedTagLikeRepository);
    }

    @Test
    public void testFindTopTagOfPast10Minutes() {
        List<PastIntervalAggregatedTagLikeDTO> topTagsByIntervalLikes = aggregatedTagLikeRepository.findTopTagsByCustomInterval("10m", 1);

        assertEquals(1, topTagsByIntervalLikes.size());

        assertEquals("hashtag5", topTagsByIntervalLikes.get(0).getTag());
        assertEquals(20, topTagsByIntervalLikes.get(0).getIntervalLikes());
    }

    @Test
    public void testFindTopTagOfPastHour() {
        List<CurrentHourAggregatedTagLikeDTO> topTagsByHourlyLikes = aggregatedTagLikeRepository.findTopTagsOfCurrentHourWindow(1);

        assertEquals(1, topTagsByHourlyLikes.size());

        assertEquals("hashtag1", topTagsByHourlyLikes.get(0).getTag());
        assertEquals(27, topTagsByHourlyLikes.get(0).getHourlyLikes());
    }

    @Test
    public void testFindTop3TagsOfPastHour() {
        List<CurrentHourAggregatedTagLikeDTO> topTagsByHourlyLikes = aggregatedTagLikeRepository.findTopTagsOfCurrentHourWindow(3);

        assertEquals(3, topTagsByHourlyLikes.size());

        assertEquals("hashtag1", topTagsByHourlyLikes.get(0).getTag());
        assertEquals(27, topTagsByHourlyLikes.get(0).getHourlyLikes());

        assertEquals("hashtag5", topTagsByHourlyLikes.get(1).getTag());
        assertEquals(20, topTagsByHourlyLikes.get(1).getHourlyLikes());

        assertEquals("hashtag3", topTagsByHourlyLikes.get(2).getTag());
        assertEquals(15, topTagsByHourlyLikes.get(2).getHourlyLikes());
    }

    @Test // FYI: This test is not deterministic because of the fact it is based on the hour window
    public void testFindTop10TagsOfPastHour() {
        List<CurrentHourAggregatedTagLikeDTO> topTagsByHourlyLikes = aggregatedTagLikeRepository.findTopTagsOfCurrentHourWindow(10);

        assertEquals(10, topTagsByHourlyLikes.size());

        assertEquals("hashtag1", topTagsByHourlyLikes.get(0).getTag());
        assertEquals(27, topTagsByHourlyLikes.get(0).getHourlyLikes());

        assertEquals("hashtag5", topTagsByHourlyLikes.get(1).getTag());
        assertEquals(20, topTagsByHourlyLikes.get(1).getHourlyLikes());

        assertEquals("hashtag3", topTagsByHourlyLikes.get(2).getTag());
        assertEquals(15, topTagsByHourlyLikes.get(2).getHourlyLikes());

        assertEquals("hashtag7", topTagsByHourlyLikes.get(3).getTag());
        assertEquals(13, topTagsByHourlyLikes.get(3).getHourlyLikes());

        assertEquals("hashtag2", topTagsByHourlyLikes.get(4).getTag());
        assertEquals(12, topTagsByHourlyLikes.get(4).getHourlyLikes());

        assertEquals("hashtag9", topTagsByHourlyLikes.get(5).getTag());
        assertEquals(11, topTagsByHourlyLikes.get(5).getHourlyLikes());

        assertEquals("hashtag10", topTagsByHourlyLikes.get(6).getTag());
        assertEquals(9, topTagsByHourlyLikes.get(6).getHourlyLikes());

        assertEquals("hashtag8", topTagsByHourlyLikes.get(7).getTag());
        assertEquals(8, topTagsByHourlyLikes.get(7).getHourlyLikes());

        assertEquals("hashtag4", topTagsByHourlyLikes.get(8).getTag());
        assertEquals(7, topTagsByHourlyLikes.get(8).getHourlyLikes());

        assertEquals("hashtag6", topTagsByHourlyLikes.get(9).getTag());
        assertEquals(5, topTagsByHourlyLikes.get(9).getHourlyLikes());
    }

    @Test
    public void testFindTopTagOfPast2Hours() {
        List<PastIntervalAggregatedTagLikeDTO> topTagsByIntervalLikes = aggregatedTagLikeRepository.findTopTagsByCustomInterval("2h", 1);

        assertEquals(1, topTagsByIntervalLikes.size());

        assertEquals("hashtag1", topTagsByIntervalLikes.get(0).getTag());
        assertEquals(54, topTagsByIntervalLikes.get(0).getIntervalLikes());
    }

    @Test
    public void testFindTopTagOfPastDay() {
        List<PastIntervalAggregatedTagLikeDTO> topTagsByIntervalLikes = aggregatedTagLikeRepository.findTopTagsByCustomInterval("1d", 1);

        assertEquals(1, topTagsByIntervalLikes.size());

        assertEquals("hashtag1", topTagsByIntervalLikes.get(0).getTag());
        assertEquals(64, topTagsByIntervalLikes.get(0).getIntervalLikes());
    }
}