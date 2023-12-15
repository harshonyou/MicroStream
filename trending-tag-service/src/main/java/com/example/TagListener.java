package com.example;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaListener(groupId = "trending-tag-service-group")
public class TagListener {

    @Topic("tags")
    void receive(@KafkaKey String tag,
                 boolean likeStatus) {
        System.out.println("LISTENER | Key: " + tag + ", Value: " + likeStatus);
    }

    @Topic("word-count")
    void receiveTrendingTags(@KafkaKey String tag,
                             long count) {
        System.out.println("Received trending tag: " + tag + " " + count);
    }
}
