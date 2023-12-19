package com.example.controller;

import com.example.service.SubscriptionService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

@Controller("/api/v1")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Post("/subscribe/{userId}/{tag}")
    public void associateUserWithTag(String userId, String tag) {
        subscriptionService.associateUserWithTag(tag, userId);
    }

    @Post("/unsubscribe/{userId}/{tag}")
    public void disassociateUserFromTag(String userId, String tag) {
        subscriptionService.disassociateUserFromTag(tag, userId);
    }
}
