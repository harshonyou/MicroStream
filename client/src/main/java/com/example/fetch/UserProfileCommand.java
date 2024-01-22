package com.example.fetch;

import com.example.api.VideoServiceHttpClient;
import com.example.dto.VideoDTO;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import picocli.CommandLine;

import java.util.List;

@CommandLine.Command(name = "user-profile", description = "Get all the videos posted by a user", mixinStandardHelpOptions = true)
public class UserProfileCommand implements Runnable {
    @CommandLine.Option(names = {"-u", "--user"}, description = "User ID", required = true)
    private String userId;

    @CommandLine.Option(names = {"--verbose"}, description = "Verbose mode")
    private boolean verbose;

    @Inject
    VideoServiceHttpClient videoServiceClient;

    @Override
    public void run() {
        HttpResponse<List<VideoDTO>> response = videoServiceClient.getVideosByUserId(userId);

        if(response.getStatus() == HttpStatus.OK) {
            System.out.println("Videos posted by user " + userId);
            response.body().stream()
                    .map(VideoDTO::formatVideoDTO)
                    .forEach(System.out::println);
        } else {
            System.out.println("Failed to get videos posted by user " + userId);
        }

        if (verbose) {
            System.out.printf(
                    "Item Size: %d%n",
                    response.body().size()
            );
        }
    }
}