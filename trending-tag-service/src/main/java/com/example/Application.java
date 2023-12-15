package com.example;

import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;

import java.util.Set;

@Singleton
public class Application {

    private TagClient tagClient;

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

    @Scheduled(fixedDelay = "5s")
    public void sendFakeUpdate() {
        String[] tags = {"micronaut", "java", "kafka", "cassandra", "docker", "kubernetes", "aws", "azure", "gcp"};
        String[] likeStatus = {"like", "dislike"};

        Tags t = new Tags();
        String randomLikeStatus = likeStatus[(int) (Math.random() * likeStatus.length)];

        int randomIndex = (int) (Math.random() * tags.length);
        Set<String> tagSet = Set.of(tags);

        t.setLikeStatus(randomLikeStatus.equals("like"));
        t.setTags(tagSet);


        // generate random string for id
        String id = "";
        for (int i = 0; i < 10; i++) {
            id += (char) ((int) (Math.random() * 26) + 97);
        }

        System.out.println("SENDING: " + t.toString());

        tagClient.send(id, t);
    }

}