package com.example.controller;

import com.example.dto.RecommendedVideoDTO;
import com.example.service.RecommendationService;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;


@MicronautTest(environments = "no-streams")
class RecommendationControllerTest {
    @Mock
    RecommendationService mockRecommendationService;

    RecommendationController recommendationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        recommendationController = new RecommendationController(mockRecommendationService);
    }

    @Test
    public void getTimelineWithEmptyList() {
        when(mockRecommendationService.getUserTimeline("123")).thenReturn(List.of());

        HttpResponse<Iterable<RecommendedVideoDTO>> response = recommendationController.getTimeline("123");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
    }

    @Test
    public  void getTimeline() {
        List<RecommendedVideoDTO> recommendedVideoDTOS = List.of(
                new RecommendedVideoDTO(UUID.fromString("c7b335b0-9f1e-11ee-9326-f54df7aaa2d1"), "title", Set.of("action", "drama"), 100L)
        );
        when(mockRecommendationService.getUserTimeline("123")).thenReturn(recommendedVideoDTOS);

        HttpResponse<Iterable<RecommendedVideoDTO>> response = recommendationController.getTimeline("123");
        assertEquals(HttpStatus.OK, response.getStatus());

        List<RecommendedVideoDTO> responseRecommendedVideoDTOS = (List<RecommendedVideoDTO>) response.body();
        assertEquals(1, responseRecommendedVideoDTOS.size());
        assertEquals(UUID.fromString("c7b335b0-9f1e-11ee-9326-f54df7aaa2d1"), responseRecommendedVideoDTOS.get(0).getId());
        assertEquals("title", responseRecommendedVideoDTOS.get(0).getTitle());
        assertEquals(Set.of("action", "drama"), responseRecommendedVideoDTOS.get(0).getAffiliatedTags());
        assertEquals(100L, responseRecommendedVideoDTOS.get(0).getViews());
    }

    @Test
    public void getRecommendationsWithEmptyList() {
        when(mockRecommendationService.getUserRecommendations("123")).thenReturn(List.of());

        HttpResponse<Iterable<RecommendedVideoDTO>> response = recommendationController.getRecommendations("123");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
    }

    @Test
    public void getRecommendations() {
        List<RecommendedVideoDTO> recommendedVideoDTOS = List.of(
                new RecommendedVideoDTO(UUID.fromString("c7b335b0-9f1e-11ee-9326-f54df7aaa2d1"), "title", Set.of("action", "drama"), 100L)
        );
        when(mockRecommendationService.getUserRecommendations("123")).thenReturn(recommendedVideoDTOS);

        HttpResponse<Iterable<RecommendedVideoDTO>> response = recommendationController.getRecommendations("123");
        assertEquals(HttpStatus.OK, response.getStatus());

        List<RecommendedVideoDTO> responseRecommendedVideoDTOS = (List<RecommendedVideoDTO>) response.body();
        assertEquals(1, responseRecommendedVideoDTOS.size());
        assertEquals(UUID.fromString("c7b335b0-9f1e-11ee-9326-f54df7aaa2d1"), responseRecommendedVideoDTOS.get(0).getId());
        assertEquals("title", responseRecommendedVideoDTOS.get(0).getTitle());
        assertEquals(Set.of("action", "drama"), responseRecommendedVideoDTOS.get(0).getAffiliatedTags());
        assertEquals(100L, responseRecommendedVideoDTOS.get(0).getViews());
    }

    @Test
    public void getRecommendationsByTagWithEmptyList() {
        when(mockRecommendationService.getUserRecommendations("123", "action")).thenReturn(List.of());

        HttpResponse<Iterable<RecommendedVideoDTO>> response = recommendationController.getRecommendations("123", "action");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
    }

    @Test
    public void getRecommendationsByTag() {
        List<RecommendedVideoDTO> recommendedVideoDTOS = List.of(
                new RecommendedVideoDTO(UUID.fromString("c7b335b0-9f1e-11ee-9326-f54df7aaa2d1"), "title", Set.of("action", "drama"), 100L)
        );
        when(mockRecommendationService.getUserRecommendations("123", "action")).thenReturn(recommendedVideoDTOS);

        HttpResponse<Iterable<RecommendedVideoDTO>> response = recommendationController.getRecommendations("123", "action");
        assertEquals(HttpStatus.OK, response.getStatus());

        List<RecommendedVideoDTO> responseRecommendedVideoDTOS = (List<RecommendedVideoDTO>) response.body();
        assertEquals(1, responseRecommendedVideoDTOS.size());
        assertEquals(UUID.fromString("c7b335b0-9f1e-11ee-9326-f54df7aaa2d1"), responseRecommendedVideoDTOS.get(0).getId());
        assertEquals("title", responseRecommendedVideoDTOS.get(0).getTitle());
        assertEquals(Set.of("action", "drama"), responseRecommendedVideoDTOS.get(0).getAffiliatedTags());
        assertEquals(100L, responseRecommendedVideoDTOS.get(0).getViews());
    }
}