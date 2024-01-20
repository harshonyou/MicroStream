package com.example.helper.scheduler;

import com.example.dto.VideoCreationEventDTO;
import com.example.dto.VideoEngagementEventDTO;
import com.example.dto.VideoFeedbackEventDTO;
import com.example.helper.producer.VideoCreationEventClient;
import com.example.helper.producer.VideoEngagementEventClient;
import com.example.helper.producer.VideoFeedbackEventClient;
import com.example.model.Tag;
import com.example.model.User;
import com.example.model.Video;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Singleton
public class KafkaEventScheduler {

    @Inject
    private VideoCreationEventClient videoCreationEventClient;

    @Inject
    private VideoEngagementEventClient videoEngagementEventClient;

    @Inject
    private VideoFeedbackEventClient videoFeedbackEventClient;

    private static final  boolean SEND_FAKE_UPDATE = false;

    @EventListener
    public void onStartup(ServerStartupEvent event) {
    }

    @Scheduled(fixedDelay = "10s")
    public void doSomething() {
        if(!SEND_FAKE_UPDATE) {
            return;
        }

        String randomUserId = String.valueOf((int) (Math.random() * 1000));
        UUID randomVideoId = UUID.randomUUID();
        String randomVideoTitle = "Video " + randomVideoId.toString();
        long randomVideoViews = (long) (Math.random() * 1000);
        Set<String> randomTags = Set.of("tag1", "tag2", "tag3");
//        Set<Tag> randomTags = Set.of(new Tag("tag1"), new Tag("tag2"), new Tag("tag3"));

        System.out.println("Posting video: " + randomVideoTitle);
        videoCreationEventClient.send(randomUserId, new VideoCreationEventDTO(randomUserId, randomVideoId, randomVideoTitle, randomTags));

        String userThatLikesEverything = "1";

        System.out.println("Liking video: " + randomVideoTitle);
        videoFeedbackEventClient.send(randomUserId, new VideoFeedbackEventDTO(randomUserId, randomVideoId, true));

        String userThatWatchesEverything = "2";

        System.out.println("Watching video: " + randomVideoTitle);
        videoEngagementEventClient.send(randomUserId, new VideoEngagementEventDTO(randomUserId, randomVideoId, Instant.now()));
    }

}
