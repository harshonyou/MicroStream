package com.example.service;

public interface SubscriptionService {

    void associateUserWithTag(String tagName, String userId); // TODO: boolean?

    void disassociateUserFromTag(String tagName, String userId);
}
