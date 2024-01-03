package com.example.producer;

import com.example.dto.TagEngagementEventDTO;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false, environments = "no-streams")
class TagSubscriptionEventClientTest {

    private final Map<String, TagEngagementEventDTO> tagEngagements = new HashMap<>();

    @MockBean(TagSubscriptionEventClient.class)
    TagSubscriptionEventClient testClient() {
        return tagEngagements::put;
    }

    @AfterEach
    public void tearDown() {
        tagEngagements.clear();
    }

    @Test
    public void testNotifyOnTagSubscribeUnsubscribe() {
        String userId = "user";
        String tag = "tag";
        boolean subscriptionStatus = true;
        TagEngagementEventDTO event = new TagEngagementEventDTO(userId, tag, subscriptionStatus);

        TagSubscriptionEventClient client = testClient();
        client.notifyOnTagSubscribeUnsubscribe("key", event);

        assertEquals(1, tagEngagements.size());
        assertTrue(tagEngagements.containsKey("key"));
        assertEquals(event, tagEngagements.get("key"));
    }
}