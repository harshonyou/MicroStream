package com.example.repository;

import com.example.model.Tag;

import java.util.Optional;

public interface TagRepository {
    Optional<Tag> findByTagName(String tagName);

    void addTag(Tag tag);

    void associateUserWithTag(String tagName, String userId);

    void disassociateUserFromTag(String tagName, String userId);
}
