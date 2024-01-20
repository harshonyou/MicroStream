package com.example.controller;

import com.example.service.SubscriptionService;
import com.example.service.TagService;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@MicronautTest(environments = "no-streams")
class SubscriptionControllerTest {
    @Mock
    SubscriptionService mockSubscriptionService;

    @Mock
    TagService mockTagService;

    SubscriptionController subscriptionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        subscriptionController = new SubscriptionController(mockSubscriptionService, mockTagService);
    }

    @Test
    public void testSubscribeUserToTag() {
        assertEquals(HttpStatus.OK, subscriptionController.subscribeUserToTag("123", "action").getStatus());
    }

    @Test
    public void testUnsubscribeUserFromTag() {
        assertEquals(HttpStatus.OK, subscriptionController.unsubscribeUserFromTag("123", "action").getStatus());
    }
}