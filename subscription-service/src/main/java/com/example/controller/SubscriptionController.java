package com.example.controller;

import com.example.service.SubscriptionService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import jakarta.validation.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Validated
@Controller("/api/v1")
public class SubscriptionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionController.class);

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    // Endpoint to subscribe a user to a tag
    @Post("/users/{userId}/tags/{tagName}/subscribe")
    public HttpResponse<Void> subscribeUserToTag(
            @PathVariable(value = "userId") @NotEmpty String userId,
            @PathVariable(value = "tagName") @NotEmpty String tagName) {
        LOGGER.info("Subscribing user ID: {} to tag: {}", userId, tagName);
        subscriptionService.subscribeUserToTag(tagName, userId);
        return HttpResponse.ok();
    }

    // Endpoint to unsubscribe a user from a tag
    @Post("/users/{userId}/tags/{tagName}/unsubscribe")
    public HttpResponse<Void> unsubscribeUserFromTag(
            @PathVariable(value = "userId") @NotEmpty String userId,
            @PathVariable(value = "tagName") @NotEmpty String tagName) {
        LOGGER.info("Unsubscribing user ID: {} from tag: {}", userId, tagName);
        subscriptionService.unsubscribeUserFromTag(tagName, userId);
        return HttpResponse.ok();
    }
}
