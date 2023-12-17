package com.example;

import com.example.dto.TagsLikeEventDTO;
import com.example.producer.TagsLikeEventClient;
import io.micronaut.runtime.Micronaut;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Inject;

import java.util.HashSet;
import java.util.Set;

public class Application {

    @Inject
    private TagsLikeEventClient tagsLikeEventClient;

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }

    @Scheduled(fixedDelay = "10s")
    public void sendFakeUpdate() {
        String[] tags = {"micronaut", "java", "kafka", "cassandra", "docker", "kubernetes", "aws", "azure", "gcp", "golang", "c++", "kotlin"};
        String[] likeStatus = {"like", "dislike"};

        TagsLikeEventDTO t = new TagsLikeEventDTO();
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

        tagsLikeEventClient.send(id, t);
    }
}