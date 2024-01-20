package com.example.service;

import com.example.dto.TagEngagementEventDTO;
import com.example.model.Tag;
import com.example.producer.TagEngagementEventClient;
import com.example.repository.Neo4jTagRepository;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@MicronautTest(transactional = false, environments = "no-streams")
class SubscriptionServiceImplTest {
    @Inject
    Neo4jTagRepository tagRepository;

    @Mock
    TagEngagementEventClient mockEventClient;

    @AfterEach
    public void tearDown() {
        tagRepository.deleteAll();
    }

    @Test
    public void testSubscribeUserToTag() {
        MockitoAnnotations.openMocks(this);
        SubscriptionService subscriptionService = new SubscriptionServiceImpl(tagRepository, mockEventClient);

        String tagName = "tag-name";
        String userId = "user-id";

        tagRepository.addTag(new Tag(tagName));
        subscriptionService.subscribeUserToTag(tagName, userId);

        verify(mockEventClient).notifyOnTagEngagementEvent(eq(userId), argThat(new ArgumentMatcher<TagEngagementEventDTO>() {
            @Override
            public boolean matches(TagEngagementEventDTO argument) {
                return argument.getUserId().equals(userId) && argument.getTag().equals(tagName) && argument.isSubscriptionStatus();
            }
        }));

        assertTrue(tagRepository.findByTagName(tagName).isPresent());
        assertEquals(tagName, tagRepository.findByTagName(tagName).get().getName());
    }

    @Test
    public void testUnsubscribeUserFromTag() {
        MockitoAnnotations.openMocks(this);
        SubscriptionService subscriptionService = new SubscriptionServiceImpl(tagRepository, mockEventClient);

        String tagName = "tag-name";
        String userId = "user-id";

        tagRepository.addTag(new Tag(tagName));
        subscriptionService.unsubscribeUserFromTag(tagName, userId);

        verify(mockEventClient).notifyOnTagEngagementEvent(eq(userId), argThat(new ArgumentMatcher<TagEngagementEventDTO>() {
            @Override
            public boolean matches(TagEngagementEventDTO argument) {
                return argument.getUserId().equals(userId) && argument.getTag().equals(tagName) && !argument.isSubscriptionStatus();
            }
        }));

        assertTrue(tagRepository.findByTagName(tagName).isPresent());
        assertEquals(tagName, tagRepository.findByTagName(tagName).get().getName());
    }
}