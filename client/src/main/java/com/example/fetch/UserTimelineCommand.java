package com.example.fetch;

import com.example.api.SubscriptionServiceHttpClient;
import com.example.dto.RecommendedVideoDTO;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(
        name = "timeline",
        description = "Get user timeline (recommended videos) based on the algorithm",
        mixinStandardHelpOptions = true
)
public class UserTimelineCommand implements Runnable {
    @CommandLine.Option(names = {"-u", "--user"}, description = "User ID", required = true)
    private String userId;

    @CommandLine.Option(names = {"--verbose"}, description = "Verbose mode")
    private boolean verbose;

    @Inject
    SubscriptionServiceHttpClient subscriptionServiceClient;

    @Override
    public void run() {
        if (verbose) {
            System.out.println("User ID: " + userId);
        }

        HttpResponse<List<RecommendedVideoDTO>> response = subscriptionServiceClient.getTimeline(userId);

        if(response.getStatus() == HttpStatus.NO_CONTENT) {
            System.out.println("No recommendations found");
            return;
        }

        System.out.println("Recommendations for user " + userId + ":");

        response.body().stream()
                .map(RecommendedVideoDTO::formatRecommendedVideoDTO)
                .forEach(System.out::println);

        if (verbose) System.out.println(response.body());
    }
}
