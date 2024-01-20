package com.example.post;

import com.example.api.VideoServiceHttpClient;
import com.example.dto.VideoDTO;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import jakarta.inject.Inject;
import picocli.CommandLine;

import java.util.Set;

@CommandLine.Command(name="post", description = "Post a video", mixinStandardHelpOptions = true)
final public class PostVideoCommand implements Runnable{
    @CommandLine.Option(names = {"-u", "--user"}, description = "User ID", required = true)
    private String userId;

    @CommandLine.Option(names = {"-t", "--title"}, description = "Video title", required = true)
    private String title;

    @CommandLine.Option(names={"-T", "--tags"}, description = "Video tags", required = true)
    private Set<String> tags;

    @CommandLine.Option(names={"--verbose"}, description = "Verbose mode")
    private boolean verbose;

    @Inject
    VideoServiceHttpClient videoServiceClient;

    @Override
    public void run() {
        if(verbose) {
            System.out.println("User ID: " + userId);
            System.out.println("Title: " + title);
            System.out.println("Tags: " + tags);
        }

        HttpResponse<VideoDTO> response = videoServiceClient.postVideo(
                userId,
                new VideoDTO(userId, null, title, tags)
        );

        if(response.getStatus() == HttpStatus.CREATED) {
            System.out.println("Video posted successfully");
            System.out.println(VideoDTO.formatVideoDTO(response.body()));
        } else {
            System.out.println("Failed to post video");
        }

        if(verbose) System.out.println(response.body());
    }
}
