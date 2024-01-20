package com.example;

import com.example.engagement.WatchVideoCommand;
import com.example.feedback.DislikeVideoCommand;
import com.example.feedback.LikeVideoCommand;
import com.example.fetch.SuggestedVideosCommand;
import com.example.fetch.UserProfileCommand;
import com.example.fetch.UserTimelineCommand;
import com.example.post.PostVideoCommand;
import com.example.tag.SubscribeTagCommand;
import com.example.tag.UnsubscribeTagCommand;
import com.example.trend.CurrentTopTagCommand;
import com.example.trend.PastTopTagCommand;
import io.micronaut.configuration.picocli.PicocliRunner;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
    name = "cli",
    description = "CLI Client for the Simplified Social Media Platform",
    mixinStandardHelpOptions = true,
    subcommands = {
        PostVideoCommand.class,
        UserProfileCommand.class,
        WatchVideoCommand.class,
        LikeVideoCommand.class,
        DislikeVideoCommand.class,
        CurrentTopTagCommand.class,
        PastTopTagCommand.class,
        SubscribeTagCommand.class,
        UnsubscribeTagCommand.class,
        SuggestedVideosCommand.class,
        UserTimelineCommand.class
    }
)
public class Cli implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "Verbose mode")
    boolean verbose;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(Cli.class, args);
    }

    public void run() {
        System.out.println("Welcome to the Simplified Social Media Platform CLI Client");
        System.out.println("Available commands can be found by running passing `--help` option to the CLI Client");

        if(verbose) System.out.println("Verbose mode enabled");
    }
}
