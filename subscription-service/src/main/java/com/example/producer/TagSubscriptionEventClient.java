package com.example.producer;

import com.example.dto.TagEngagementEventDTO;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface TagSubscriptionEventClient {

    @Topic("tag-engagement-events")
    void notifyOnTagSubscribeUnsubscribe(@KafkaKey String key, TagEngagementEventDTO event);
}
