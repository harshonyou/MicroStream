package com.example.helper.scheduler;

import com.example.dto.VideoFeedbackEventDTO;
import com.example.helper.producer.TagsLikeEventClient;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Singleton
public class TagsLikeEventSchedule {
    @Inject
    private TagsLikeEventClient tagsLikeEventClient;

    private final Map<String, Long> tagLikeMap = new HashMap<>();

    private static final  boolean SEND_FAKE_UPDATE = false;

    @Scheduled(fixedDelay = "10s")
    public void sendFakeUpdate() {
        if(!SEND_FAKE_UPDATE) {
            return;
        }

        String[] tags = {"micronaut", "java", "kafka", "cassandra", "docker", "kubernetes", "aws", "azure", "gcp", "golang", "c++", "kotlin"};
        String[] likeStatus = {"like", "dislike"};

        VideoFeedbackEventDTO t = new VideoFeedbackEventDTO();
        String randomLikeStatus = likeStatus[(int) (Math.random() * likeStatus.length)];

        Set<String> tagSet = new HashSet<>();
        String first = tags[(int) (Math.random() * tags.length)];
        String second = tags[(int) (Math.random() * tags.length)];
        String third = tags[(int) (Math.random() * tags.length)];

        tagLikeMap.put(first, tagLikeMap.getOrDefault(first, 0L) + 1);
        tagLikeMap.put(second, tagLikeMap.getOrDefault(second, 0L) + 1);
        tagLikeMap.put(third, tagLikeMap.getOrDefault(third, 0L) + 1);

        tagSet.add(first);
        tagSet.add(second);
        tagSet.add(third);
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
        System.out.println("TAGS LIKE MAP | " + "\t " + tagLikeMap.toString());

        tagsLikeEventClient.send(id, t);
    }
}
