package com.example;

import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;

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

    @Scheduled(fixedDelay = "10s")
    public void sendFakeUpdate() {
        String[] tags = {"micronaut", "java", "kafka", "cassandra", "docker", "kubernetes", "aws", "azure", "gcp"};

        int randomIndex = (int) (Math.random() * tags.length);
        boolean randomLikeStatus = Math.random() > 0.5;

        tagClient.send(tags[randomIndex], randomLikeStatus);
    }

}