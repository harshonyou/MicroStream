package com.example.feedback;

import com.example.api.VideoServiceHttpClient;
import com.example.dto.VideoFeedbackDTO;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import picocli.CommandLine;

import java.util.UUID;

@CommandLine.Command(name = "like-video", description = "Like a video by video id", mixinStandardHelpOptions = true)
public class LikeVideoCommand implements Runnable {
    @CommandLine.Option(names = {"-u", "--userid"}, description = "User ID")
    private String userId;

    @CommandLine.Option(names = {"-v", "--video"}, description = "Video ID")
    private String videoId;

    @CommandLine.Option(names = {"--verbose"}, description = "Verbose mode")
    private boolean verbose;

    @Inject
    VideoServiceHttpClient videoServiceClient;

    public void run() {
        if (verbose) {
            System.out.println("User ID: " + userId);
            System.out.println("Video ID: " + videoId);
        }

        HttpResponse<VideoFeedbackDTO> response = videoServiceClient.likeVideo(
                userId,
                UUID.fromString(videoId)
        );

        if(response.getStatus() == HttpStatus.OK) {
            System.out.println("Video liked successfully");
            System.out.println(VideoFeedbackDTO.formatVideoFeedbackDTO(response.body()));
        } else {
            System.out.println("Failed to like video");
        }

        if(verbose) System.out.println(response.body());
    }
}