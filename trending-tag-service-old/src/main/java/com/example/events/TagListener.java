package com.example.events;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaListener(groupId = "trending-tag-service-group")
public class TagListener {

    @Topic("tags")
    void receive(@KafkaKey String id,
                 Tags tags) {
        System.out.println("LISTENING | " + "\t" +" Key: " + id + ", Value: " + tags);
    }

    @Topic("word-count")
    void receiveTrendingTags(@KafkaKey String tag,
                             long count) {
        System.out.println("Received trending tag: " + tag + " " + count);
    }
}
