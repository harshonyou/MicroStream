package com.example;

import io.micronaut.runtime.Micronaut;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;

import java.util.HashSet;
import java.util.Set;

@Singleton
public class Application {

    private final TagClient tagClient;

    public Application(TagClient tagClient) {
        this.tagClient = tagClient;
    }

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }

    @Scheduled(fixedDelay = "10s")
    public void sendFakeUpdate() {
        String[] tags = {"micronaut", "java", "kafka", "cassandra", "docker", "kubernetes", "aws", "azure", "gcp", "golang", "c++", "kotlin"};
        String[] likeStatus = {"like", "dislike"};

        Tags t = new Tags();
        String randomLikeStatus = likeStatus[(int) (Math.random() * likeStatus.length)];

        Set<String> tagSet = new HashSet<>();
        tagSet.add(tags[(int) (Math.random() * tags.length)]);
        tagSet.add(tags[(int) (Math.random() * tags.length)]);
        tagSet.add(tags[(int) (Math.random() * tags.length)]);
//        Set<String> tagSet = Set.of(tags);

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
    }

}