package com.example.controller;

import com.example.dto.PastIntervalAggregatedTagLikeDTO;
import com.example.model.AggregatedTagLike;
import com.example.dto.CurrentHourAggregatedTagLikeDTO;
import com.example.repository.AggregatedTagLikeRepository;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import jakarta.validation.constraints.NotEmpty;

import static com.example.validator.DurationValidator.isValidDuration;

@Controller("/api/v1")
public class AggregatedTagLikeController {

    @Inject
    AggregatedTagLikeRepository tagLikeRepository;

    @Post("/")
    public void likeTag(@Body AggregatedTagLike tag) {
        tagLikeRepository.save(tag);
    }

    @Get("/")
    public Iterable<AggregatedTagLike> getTags() {
        return tagLikeRepository.findAll();
    }

    @Get("/hashtags/top/current")
    public Iterable<CurrentHourAggregatedTagLikeDTO> getTopHashtags(
            @QueryValue(value = "limit") Integer limit) {
        if(limit > 100) {
            limit = 100;
        }

        return tagLikeRepository.findTopTagsByHourlyLikes(limit);
    }

    @Get("/hashtags/top/past")
    public Iterable<PastIntervalAggregatedTagLikeDTO> getTopHashtags(
            @QueryValue(value = "interval") @NotEmpty String interval,
            @QueryValue(value = "limit") Integer limit) {
        if(!isValidDuration(interval)) {
            throw new IllegalArgumentException("Invalid interval"); // TODO: Send back HTTP 400
        }

        if(limit > 100) {
            limit = 100;
        }

        return tagLikeRepository.findTopTagsByCustomInterval(interval, limit);
    }
}