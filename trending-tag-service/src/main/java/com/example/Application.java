package com.example;

import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Map;
import java.util.Set;

@Singleton
public class Application {

    private TagClient tagClient;

    @Inject
    TopTags topTags;

    public Application(TagClient tagClient) {
        this.tagClient = tagClient;
    }

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }

//    @EventListener
//    void startup(StartupEvent event) {
//        tagClient.send("micronaut");
//    }

    @Scheduled(fixedDelay = "10s")
    public void sendFakeUpdate() {
        String[] tags = {"micronaut", "java", "kafka", "cassandra", "docker", "kubernetes", "aws", "azure", "gcp"};
        String[] likeStatus = {"like", "dislike"};

        Tags t = new Tags();
        String randomLikeStatus = likeStatus[(int) (Math.random() * likeStatus.length)];

        int randomIndex = (int) (Math.random() * tags.length);
        Set<String> tagSet = Set.of(tags);

        t.setLikeStatus(randomLikeStatus.equals("like"));
        t.setTags(tagSet);
        t.setLikeStatus(true);


        // generate random string for id
        String id = "";
        for (int i = 0; i < 10; i++) {
            id += (char) ((int) (Math.random() * 26) + 97);
        }

        System.out.println("SENDING | " + "\t " + "Key: "+id+" , Value:"+ t.toString());

        tagClient.send(id, t);


        Map<String, Long> topHashtagCounts = topTags.getTopHashtags();

        // Print the top hashtags and their counts
        System.out.println("Top 10 Hashtags:");
        topHashtagCounts.forEach((topHashtag, topCount) -> {
            System.out.print(topHashtag + ": " + topCount + " | ");
        });
        System.out.println();
    }

}