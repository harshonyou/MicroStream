package com.example;

import com.example.engagement.WatchVideo;
import com.example.feedback.DislikeVideo;
import com.example.feedback.LikeVideo;
import com.example.fetch.SuggestedVideos;
import com.example.fetch.UserProfile;
import com.example.fetch.UserTimeline;
import com.example.post.PostVideo;
import com.example.tag.SubscribeTag;
import com.example.tag.UnsubscribeTag;
import com.example.trend.CurrentTopTag;
import com.example.trend.PastTopTag;
import io.micronaut.configuration.picocli.PicocliRunner;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "cli", description = "...",
    mixinStandardHelpOptions = true, subcommands = {
        PostVideo.class,
        UserProfile.class,
        WatchVideo.class,
        LikeVideo.class,
        DislikeVideo.class,
        CurrentTopTag.class,
        PastTopTag.class,
        SubscribeTag.class,
        UnsubscribeTag.class,
        SuggestedVideos.class,
        UserTimeline.class
    })
public class CliCommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(CliCommand.class, args);
    }

    public void run() {
        // business logic here
        if (verbose) {
            System.out.println("Hi!");
        }
    }
}
