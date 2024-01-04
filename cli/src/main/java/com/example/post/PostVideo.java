package com.example.post;

import picocli.CommandLine;

import java.util.Set;

@CommandLine.Command(name="post", description = "Post a video", mixinStandardHelpOptions = true)
final public class PostVideo implements Runnable{
    @CommandLine.Option(names = {"-u", "--user"}, description = "User ID", required = true)
    private String userId;

    @CommandLine.Option(names = {"-t", "--title"}, description = "Video title", required = true)
    private String title;

    @CommandLine.Option(names={"-T", "--tags"}, description = "Video tags", required = true)
    private Set<String> tags;

    @CommandLine.Option(names={"--verbose"}, description = "Verbose mode")
    private boolean verbose;

    @Override
    public void run() {
        System.out.println("Posting a video");
    }
}
