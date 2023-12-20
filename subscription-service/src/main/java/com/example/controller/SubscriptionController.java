package com.example.controller;

import com.example.service.SubscriptionService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import jakarta.validation.constraints.NotEmpty;

@Controller("/api/v1")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Post("/users/{userId}/tags/{tagName}/subscribe")
    public void subscribeUserToTag(
            @PathVariable(value = "userId") @NotEmpty String userId,
            @PathVariable(value = "tagName") @NotEmpty String tagName) {
        subscriptionService.subscribeUserToTag(tagName, userId);
    }

    @Post("/users/{userId}/tags/{tagName}/unsubscribe")
    public void unsubscribeUserFromTag(
            @PathVariable(value = "userId") @NotEmpty String userId,
            @PathVariable(value = "tagName") @NotEmpty String tagName) {
        subscriptionService.unsubscribeUserFromTag(tagName, userId);
    }
}
