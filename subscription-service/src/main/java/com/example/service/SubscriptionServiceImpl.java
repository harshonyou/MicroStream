package com.example.service;

import com.example.dto.TagEngagementEventDTO;
import com.example.producer.TagSubscriptionEventClient;
import com.example.repository.Neo4jTagRepository;
import com.example.repository.TagRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class SubscriptionServiceImpl implements SubscriptionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    private final TagRepository tagRepository;

    private final TagSubscriptionEventClient eventClient;
    
    public SubscriptionServiceImpl(Neo4jTagRepository tagRepository, TagSubscriptionEventClient eventClient) {
        this.tagRepository = tagRepository;
        this.eventClient = eventClient;
    }

    @Override
    public void subscribeUserToTag(String tagName, String userId) {
        LOGGER.info("Subscribing user ID: {} to tag: {}", userId, tagName);
        tagRepository.associateUserWithTag(tagName, userId);
        eventClient.notifyOnTagSubscribeUnsubscribe(userId, new TagEngagementEventDTO(userId, tagName, true));
    }

    @Override
    public void unsubscribeUserFromTag(String tagName, String userId) {
        LOGGER.info("Unsubscribing user ID: {} from tag: {}", userId, tagName);
        tagRepository.disassociateUserFromTag(tagName, userId);
        eventClient.notifyOnTagSubscribeUnsubscribe(userId, new TagEngagementEventDTO(userId, tagName, false));
    }
}
