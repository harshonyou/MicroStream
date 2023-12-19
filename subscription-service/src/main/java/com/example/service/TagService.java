package com.example.service;

import com.example.dto.TagDTO;

import java.util.Optional;
import java.util.Set;

public interface TagService {
    void addTag(String tagName);
    void addTags(Set<String> tagNames);
    Optional<TagDTO> findByName(String tagName);
}
