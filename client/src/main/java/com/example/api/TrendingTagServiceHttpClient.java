package com.example.api;

import com.example.dto.CurrentHourAggregatedTagLikeDTO;
import com.example.dto.PastIntervalAggregatedTagLikeDTO;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;

import java.util.List;

@Client("${trending-tag-service.api.url}")
public interface TrendingTagServiceHttpClient {
    @Get("/api/v1/hashtags/top/current")
    HttpResponse<List<CurrentHourAggregatedTagLikeDTO>> getCurrentTopTags(
            @QueryValue(value = "limit") Integer limit
    );

    @Get("/api/v1/hashtags/top/past")
    HttpResponse<List<PastIntervalAggregatedTagLikeDTO>> getPastTopTags(
            @QueryValue(value = "interval") String interval,
            @QueryValue(value = "limit") Integer limit
    );
}
