package com.example.helper.consumer;

import com.example.dto.TagsLikeEventDTO;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaListener(groupId = "trending-tag-service-group")
public class TagsLikeEventListener {
    @Topic("tags-like-event")
    void receive(@KafkaKey String id,
                 TagsLikeEventDTO tags) {
        System.out.println("LISTENING | " + "\t" +" Key: " + id + ", Value: " + tags);
    }
}
