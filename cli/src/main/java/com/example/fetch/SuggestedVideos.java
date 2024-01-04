package com.example.fetch;

import com.example.api.SubscriptionServiceHttpClient;
import com.example.dto.RecommendedVideoDTO;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(
        name = "suggest-videos",
        description = "Get all the recommended videos based on all the subscribed tag or specific tag if mentioned",
        mixinStandardHelpOptions = true
)
public class SuggestedVideos implements Runnable {
    @CommandLine.Option(names = {"-u", "--user"}, description = "User ID", required = true)
    private String userId;

    @CommandLine.Option(names = {"-t", "--tag"}, description = "Tag name")
    private String tagName;

    @CommandLine.Option(names = {"--verbose"}, description = "Verbose mode")
    private boolean verbose;

    @Inject
    SubscriptionServiceHttpClient subscriptionServiceClient;

    @Override
    public void run() {
        if (verbose) {
            System.out.println("User ID: " + userId);
            System.out.println("Tag name: " + tagName);
        }

        HttpResponse<List<RecommendedVideoDTO>> response;

        if (tagName == null) {
            System.out.println("Getting recommendations for user " + userId + "...");
            response = subscriptionServiceClient.getRecommendations(userId);
        } else {
            System.out.println("Getting recommendations for user " + userId + " and tag '" + tagName + "'...");
            response = subscriptionServiceClient.getRecommendations(userId, tagName);
        }

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