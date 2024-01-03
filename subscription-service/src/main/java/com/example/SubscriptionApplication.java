package com.example;

import io.micronaut.runtime.Micronaut;

public class SubscriptionApplication {

//    @Inject
//    private VideoRepository videoRepository;
//
//    @Inject
//    private UserRepository userRepository;

    public static void main(String[] args) {
        Micronaut.run(SubscriptionApplication.class, args);
    }

    // On micronaut is ready event
//    @EventListener
//    public void onStartup(ServerStartupEvent event) {
//        System.out.println("Micronaut server started");
//
//        userRepository.addUser(new User("1", "Likes Everything"));
//        userRepository.addUser(new User("2", "Watches Everything"));
//    }
//
//    @Scheduled(fixedDelay = "10s")
//    public void doSomething() {
//        String randomUserId = String.valueOf((int) (Math.random() * 1000));
//        UUID randomVideoId = UUID.randomUUID();
//        String randomVideoTitle = "Video " + randomVideoId.toString();
//        long randomVideoViews = (long) (Math.random() * 1000);
//        Set<Tag> randomTags = Set.of(new Tag("tag1"), new Tag("tag2"), new Tag("tag3"));
//
//        System.out.println("Posting video: " + randomVideoTitle);
//        videoRepository.postVideo(randomUserId, new Video(randomVideoId, randomVideoTitle, randomVideoViews), randomTags);
//
//        String userThatLikesEverything = "1";
//
//        System.out.println("Liking video: " + randomVideoTitle);
//        videoRepository.likeVideo(userThatLikesEverything, randomVideoId);
//
//        String userThatWatchesEverything = "2";
//
//        System.out.println("Watching video: " + randomVideoTitle);
//        videoRepository.watchVideo(userThatWatchesEverything, randomVideoId);
//    }
}