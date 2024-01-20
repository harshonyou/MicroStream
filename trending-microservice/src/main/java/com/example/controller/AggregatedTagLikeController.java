package com.example.controller;

import com.example.dto.PastIntervalAggregatedTagLikeDTO;
import com.example.dto.CurrentHourAggregatedTagLikeDTO;
import com.example.repository.AggregatedTagLikeRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.example.validator.DurationValidator.isValidDuration;

@Validated
@Controller("/api/v1")
public class AggregatedTagLikeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AggregatedTagLikeController.class);

    @Inject
    AggregatedTagLikeRepository tagLikeRepository;

    // Endpoint to get top hashtags of the current hour
    @Get("/hashtags/top/current")
    public HttpResponse<Iterable<CurrentHourAggregatedTagLikeDTO>> getCurrentTopHashtags(
            @QueryValue(value = "limit") Integer limit) {
        LOGGER.info("Fetching current top hashtags with limit: {}", limit);
        if(limit > 100) {
            limit = 100;
        }

        List<CurrentHourAggregatedTagLikeDTO> tags = tagLikeRepository.findTopTagsOfCurrentHourWindow(limit);

        if (tags.isEmpty()) {
            LOGGER.info("No top hashtags found for the current hour");
            return HttpResponse.noContent();
        }

        return HttpResponse.ok(tags);
    }

    @Get("/hashtags/top/past")
    public HttpResponse<Iterable<PastIntervalAggregatedTagLikeDTO>> getPastTopHashtags(
            @QueryValue(value = "interval") @NotEmpty String interval,
            @QueryValue(value = "limit") Integer limit) {
        LOGGER.info("Fetching past top hashtags for interval: {} with limit: {}", interval, limit);
        if(!isValidDuration(interval)) {
            LOGGER.warn("Invalid interval: {}", interval);
            return HttpResponse.badRequest();
        }

        if(limit > 100) {
            limit = 100;
        }

        List<PastIntervalAggregatedTagLikeDTO> tags = tagLikeRepository.findTopTagsByCustomInterval(interval, limit);

        if (tags.isEmpty()) {
            LOGGER.info("No top hashtags found for past interval: {}", interval);
            return HttpResponse.noContent();
        }

        return HttpResponse.ok(tags);
    }
}