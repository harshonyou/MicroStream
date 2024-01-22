package com.example.trend;

import com.example.api.TrendingTagServiceHttpClient;
import com.example.dto.PastIntervalAggregatedTagLikeDTO;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(
        name = "past-top",
        description = "Get the past top trending tags",
        mixinStandardHelpOptions = true
)
public class PastTopTagCommand implements Runnable {
    @CommandLine.Option(
            names = {"-i", "--interval"},
            description = "Interval of time in past to query (m for minute, h for hour, d for day)",
            defaultValue = "1h"
    )
    private String interval;
    @CommandLine.Option(
            names = {"-l", "--limit"},
            description = "Limit of tags to return",
            defaultValue = "10"
    )
    private Integer limit;

    @CommandLine.Option(names = {"--verbose"}, description = "Verbose mode")
    private boolean verbose;

    @Inject
    TrendingTagServiceHttpClient trendingTagServiceClient;

    @Override
    public void run() {
        if (verbose) {
            System.out.println("Interval: " + interval);
            System.out.println("Limit: " + limit);
        }

        HttpResponse<List<PastIntervalAggregatedTagLikeDTO>> response = trendingTagServiceClient.getPastTopTags(interval, limit);

        if(response.getStatus() == HttpStatus.NO_CONTENT) {
            System.out.println("No tags found");
        } else {
            System.out.println("Past top tags in the past " + interval);
            response.body().stream()
                    .map(PastIntervalAggregatedTagLikeDTO::formatPastIntervalAggregatedTagLikeDTO)
                    .forEach(System.out::println);
        }

        if(verbose) System.out.println(response.body());
    }
}