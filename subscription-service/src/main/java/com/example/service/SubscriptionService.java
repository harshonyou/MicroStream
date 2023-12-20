package com.example.service;

public interface SubscriptionService {

    void subscribeUserToTag(String tagName, String userId); // TODO: boolean?

    void unsubscribeUserFromTag(String tagName, String userId);
}
