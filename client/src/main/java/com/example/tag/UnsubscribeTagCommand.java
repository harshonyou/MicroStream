package com.example.tag;

import com.example.api.SubscriptionServiceHttpClient;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import picocli.CommandLine;

@CommandLine.Command(name = "unsubscribe", description = "Unsubscribe a tag", mixinStandardHelpOptions = true)
public class UnsubscribeTagCommand implements Runnable {
    @CommandLine.Option(names = {"-u", "--user"}, description = "User ID", required = true)
    private String userId;

    @CommandLine.Option(names = {"-t", "--tag"}, description = "Tag Name", required = true)
    private String tagName;

    @CommandLine.Option(names = {"--verbose"}, description = "Verbose mode")
    private boolean verbose;

    @Inject
    SubscriptionServiceHttpClient subscriptionServiceClient;

    @Override
    public void run() {
        if (verbose) {
            System.out.println("User ID: " + userId);
            System.out.println("Tag Name: " + tagName);
        }

        HttpResponse<Void> response = subscriptionServiceClient.unsubscribe(
                userId,
                tagName
        );

        if(response.getStatus() == HttpStatus.OK) {
            System.out.println("Unsubscribed successfully");
        } else {
            System.out.println("Failed to unsubscribe");
        }

        if(verbose) System.out.println(response.body());
    }
}
