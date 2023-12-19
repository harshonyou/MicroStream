package com.example.service;

import com.example.repository.TagRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class SubscriptionServiceImpl implements SubscriptionService {

    @Inject
    private TagRepository tagRepository;

    @Override
    public void associateUserWithTag(String tagName, String userId) {
        tagRepository.associateUserWithTag(tagName, userId);
    }

    @Override
    public void disassociateUserFromTag(String tagName, String userId) {
        tagRepository.disassociateUserFromTag(tagName, userId);
    }
}
