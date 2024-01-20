package com.example.controller;

import com.example.service.SubscriptionService;
import com.example.service.TagService;
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
    private final TagService tagService;

    public SubscriptionController(SubscriptionService subscriptionService, TagService tagService) {
        this.subscriptionService = subscriptionService;
        this.tagService = tagService;
    }

    // Endpoint to subscribe a user to a tag
    @Post("/users/{userId}/tags/{tagName}/subscribe")
    public HttpResponse<Void> subscribeUserToTag(
            @PathVariable(value = "userId") @NotEmpty String userId,
            @PathVariable(value = "tagName") @NotEmpty String tagName) {
        LOGGER.info("Subscribing user ID: {} to tag: {}", userId, tagName);
        tagService.addTag(tagName);
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
