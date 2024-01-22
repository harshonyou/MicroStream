package com.example.trend;

// CommandLine.Command to get the current hour window top trending tags
// Takes in args for the limit of tags to return and use TrendingTagServiceHttpClient getCurrentTopHashtags()

import com.example.api.TrendingTagServiceHttpClient;
import com.example.dto.CurrentHourAggregatedTagLikeDTO;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(
        name = "current-top",
        description = "Get the current hour window [from 0th minute to 59th minute] top trending tags",
        mixinStandardHelpOptions = true
)
public class CurrentTopTagCommand implements Runnable {
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
            System.out.println("Limit: " + limit);
        }

        HttpResponse<List<CurrentHourAggregatedTagLikeDTO>> response = trendingTagServiceClient.getCurrentTopTags(limit);

        if(response.getStatus() == HttpStatus.NO_CONTENT) {
            System.out.println("No tags found");
        } else {
            System.out.println("Current top tags in the current hour window [from 0th minute to 59th minute]");
            response.body().stream()
                    .map(CurrentHourAggregatedTagLikeDTO::formatCurrentHourAggregatedTagLikeDTO)
                    .forEach(System.out::println);
        }

        if(verbose) System.out.println(response.body());
    }
}

