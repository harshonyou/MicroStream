package com.example.engagement;

import com.example.api.VideoServiceHttpClient;
import com.example.dto.VideoEngagementDTO;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import picocli.CommandLine;

import java.util.UUID;

@CommandLine.Command(name = "watch-video", description = "Watch a video by video id", mixinStandardHelpOptions = true)
public class WatchVideoCommand implements Runnable {
    @CommandLine.Option(names = {"-u", "--user"}, description = "User ID", required = true)
    private String userId;

    @CommandLine.Option(names = {"-v", "--video"}, description = "Video ID", required = true)
    private String videoId;

    @CommandLine.Option(names = {"--verbose"}, description = "Verbose mode")
    private boolean verbose;

    @Inject
    VideoServiceHttpClient videoServiceClient;

    @Override
    public void run() {
        if (verbose) {
            System.out.println("User ID: " + userId);
            System.out.println("Video ID: " + videoId);
        }

        HttpResponse<VideoEngagementDTO> response = videoServiceClient.watchVideo(
                userId,
                UUID.fromString(videoId)
        );

        if(response.getStatus() == HttpStatus.OK) {
            System.out.println("Video watched successfully");
            System.out.println(VideoEngagementDTO.formatVideoEngagementDTO(response.body()));
        } else {
            System.out.println("Failed to watch video");
        }

        if(verbose) System.out.println(response.body());
    }
}