package com.example.service;

import com.example.dto.TagEngagementEventDTO;
import com.example.producer.TagSubscriptionEventClient;
import com.example.repository.Neo4jTagRepository;
import com.example.repository.TagRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class SubscriptionServiceImpl implements SubscriptionService {

    private final TagRepository tagRepository;

    private final TagSubscriptionEventClient eventClient;
    
    public SubscriptionServiceImpl(Neo4jTagRepository tagRepository, TagSubscriptionEventClient eventClient) {
        this.tagRepository = tagRepository;
        this.eventClient = eventClient;
    }

    @Override
    public void subscribeUserToTag(String tagName, String userId) {
        tagRepository.associateUserWithTag(tagName, userId);
        eventClient.notifyOnTagSubscribeUnsubscribe(userId, new TagEngagementEventDTO(userId, tagName, true));
    }

    @Override
    public void unsubscribeUserFromTag(String tagName, String userId) {
        tagRepository.disassociateUserFromTag(tagName, userId);
        eventClient.notifyOnTagSubscribeUnsubscribe(userId, new TagEngagementEventDTO(userId, tagName, false));
    }
}
