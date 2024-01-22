package com.example.controller;

import com.example.dto.CurrentHourAggregatedTagLikeDTO;
import com.example.dto.PastIntervalAggregatedTagLikeDTO;
import com.example.repository.AggregatedTagLikeRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.helper.AggregatedTagLikeDummyData.setUpDummyData;
import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false, environments = "no-streams")
class AggregatedTagLikeControllerTest {
    @Inject
    AggregatedTagLikeRepository aggregatedTagLikeRepository;

    @Inject
    AggregatedTagLikeController aggregatedTagLikeController;

    @BeforeEach
    public void setUp() {
        aggregatedTagLikeRepository.deleteAll();
        assertEquals(0, aggregatedTagLikeRepository.count());
    }

    @Test
    public void testGetTopTagsOfCurrentHourWithEmptyList() {
        HttpResponse<Iterable<CurrentHourAggregatedTagLikeDTO>> response = aggregatedTagLikeController.getCurrentTopHashtags(10);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
    }

    @Test
    public void testGetTopTagsOfCurrentHourWithHighLimit() {
        setUpDummyData(aggregatedTagLikeRepository);

        HttpResponse<Iterable<CurrentHourAggregatedTagLikeDTO>> response = aggregatedTagLikeController.getCurrentTopHashtags(101);
        assertEquals(HttpStatus.OK, response.getStatus());

        List<CurrentHourAggregatedTagLikeDTO> currentHourAggregatedTagLikeDTOList = (List<CurrentHourAggregatedTagLikeDTO>) response.body();
        assertTrue(currentHourAggregatedTagLikeDTOList.size() <= 100);
    }

    @Test // FYI: This test is not deterministic because of the fact it is based on the hour window
    public void testGetTop10TagsOfCurrentHour() {
        setUpDummyData(aggregatedTagLikeRepository);

        HttpResponse<Iterable<CurrentHourAggregatedTagLikeDTO>> response = aggregatedTagLikeController.getCurrentTopHashtags(10);
        assertEquals(HttpStatus.OK, response.getStatus());

        List<CurrentHourAggregatedTagLikeDTO> currentHourAggregatedTagLikeDTOList = (List<CurrentHourAggregatedTagLikeDTO>) response.body();
        assertEquals(10, currentHourAggregatedTagLikeDTOList.size());

        assertEquals("hashtag1", currentHourAggregatedTagLikeDTOList.get(0).getTag());
        assertEquals(27, currentHourAggregatedTagLikeDTOList.get(0).getHourlyLikes());

        assertEquals("hashtag5", currentHourAggregatedTagLikeDTOList.get(1).getTag());
        assertEquals(20, currentHourAggregatedTagLikeDTOList.get(1).getHourlyLikes());

        assertEquals("hashtag3", currentHourAggregatedTagLikeDTOList.get(2).getTag());
        assertEquals(15, currentHourAggregatedTagLikeDTOList.get(2).getHourlyLikes());

        assertEquals("hashtag7", currentHourAggregatedTagLikeDTOList.get(3).getTag());
        assertEquals(13, currentHourAggregatedTagLikeDTOList.get(3).getHourlyLikes());

        assertEquals("hashtag2", currentHourAggregatedTagLikeDTOList.get(4).getTag());
        assertEquals(12, currentHourAggregatedTagLikeDTOList.get(4).getHourlyLikes());

        assertEquals("hashtag9", currentHourAggregatedTagLikeDTOList.get(5).getTag());
        assertEquals(11, currentHourAggregatedTagLikeDTOList.get(5).getHourlyLikes());

        assertEquals("hashtag10", currentHourAggregatedTagLikeDTOList.get(6).getTag());
        assertEquals(9, currentHourAggregatedTagLikeDTOList.get(6).getHourlyLikes());

        assertEquals("hashtag8", currentHourAggregatedTagLikeDTOList.get(7).getTag());
        assertEquals(8, currentHourAggregatedTagLikeDTOList.get(7).getHourlyLikes());

        assertEquals("hashtag4", currentHourAggregatedTagLikeDTOList.get(8).getTag());
        assertEquals(7, currentHourAggregatedTagLikeDTOList.get(8).getHourlyLikes());

        assertEquals("hashtag6", currentHourAggregatedTagLikeDTOList.get(9).getTag());
        assertEquals(5, currentHourAggregatedTagLikeDTOList.get(9).getHourlyLikes());
    }

    @Test
    public void testFindTopTagOfCustomIntervalWithEmptyList() {
        HttpResponse<Iterable<PastIntervalAggregatedTagLikeDTO>> response = aggregatedTagLikeController.getPastTopHashtags("1d1h1m", 1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
    }

    @Test
    public void testFindTopTagOfCustomIntervalWithWrongInterval() {
        HttpResponse<Iterable<PastIntervalAggregatedTagLikeDTO>> response = aggregatedTagLikeController.getPastTopHashtags("123xyz", 1);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
    }

    @Test
    public void testFindTopTagOfCustomIntervalWithHighLimit() {
        setUpDummyData(aggregatedTagLikeRepository);

        HttpResponse<Iterable<PastIntervalAggregatedTagLikeDTO>> response = aggregatedTagLikeController.getPastTopHashtags("1d1h1m", 101);
        assertEquals(HttpStatus.OK, response.getStatus());

        List<PastIntervalAggregatedTagLikeDTO> pastIntervalAggregatedTagLikeDTOList = (List<PastIntervalAggregatedTagLikeDTO>) response.body();
        assertTrue(pastIntervalAggregatedTagLikeDTOList.size() <= 100);
    }

    @Test
    public void testFindTopTagOfPast10Minutes() {
        setUpDummyData(aggregatedTagLikeRepository);

        HttpResponse<Iterable<PastIntervalAggregatedTagLikeDTO>> response = aggregatedTagLikeController.getPastTopHashtags("10m", 1);
        assertEquals(HttpStatus.OK, response.getStatus());

        List<PastIntervalAggregatedTagLikeDTO> pastIntervalAggregatedTagLikeDTOList = (List<PastIntervalAggregatedTagLikeDTO>) response.body();
        assertEquals(1, pastIntervalAggregatedTagLikeDTOList.size());
        assertEquals("hashtag5", pastIntervalAggregatedTagLikeDTOList.get(0).getTag());
        assertEquals(20, pastIntervalAggregatedTagLikeDTOList.get(0).getIntervalLikes());
    }

    @Test
    public void testFindTopTagOfPastDay() {
        setUpDummyData(aggregatedTagLikeRepository);

        HttpResponse<Iterable<PastIntervalAggregatedTagLikeDTO>> response = aggregatedTagLikeController.getPastTopHashtags("1d", 1);
        assertEquals(HttpStatus.OK, response.getStatus());

        List<PastIntervalAggregatedTagLikeDTO> pastIntervalAggregatedTagLikeDTOList = (List<PastIntervalAggregatedTagLikeDTO>) response.body();
        assertEquals(1, pastIntervalAggregatedTagLikeDTOList.size());
        assertEquals("hashtag1", pastIntervalAggregatedTagLikeDTOList.get(0).getTag());
        assertEquals(64, pastIntervalAggregatedTagLikeDTOList.get(0).getIntervalLikes());
    }
}