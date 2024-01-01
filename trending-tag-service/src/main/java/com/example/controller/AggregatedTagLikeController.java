package com.example.controller;

import com.example.dto.PastIntervalAggregatedTagLikeDTO;
import com.example.model.AggregatedTagLike;
import com.example.dto.CurrentHourAggregatedTagLikeDTO;
import com.example.repository.AggregatedTagLikeRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

import static com.example.validator.DurationValidator.isValidDuration;

@Controller("/api/v1")
public class AggregatedTagLikeController {
    @Inject
    AggregatedTagLikeRepository tagLikeRepository;

    @Get("/hashtags/top/current")
    public HttpResponse<Iterable<CurrentHourAggregatedTagLikeDTO>> getTopHashtags(
            @QueryValue(value = "limit") Integer limit) {
        if(limit > 100) {
            limit = 100;
        }

        List<CurrentHourAggregatedTagLikeDTO> tags = tagLikeRepository.findTopTagsByHourlyLikes(limit);

        if (tags.isEmpty()) {
            return HttpResponse.noContent();
        }

        return HttpResponse.ok(tags);
    }

    @Get("/hashtags/top/past")
    public HttpResponse<Iterable<PastIntervalAggregatedTagLikeDTO>> getTopHashtags(
            @QueryValue(value = "interval") @NotEmpty String interval,
            @QueryValue(value = "limit") Integer limit) {
        if(!isValidDuration(interval)) {
            return HttpResponse.badRequest();
        }

        if(limit > 100) {
            limit = 100;
        }

        List<PastIntervalAggregatedTagLikeDTO> tags = tagLikeRepository.findTopTagsByCustomInterval(interval, limit);

        if (tags.isEmpty()) {
            return HttpResponse.noContent();
        }

        return HttpResponse.ok(tags);
    }
}