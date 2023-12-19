package com.example.service;

import com.example.dto.TagDTO;

import java.util.Optional;

public interface TagService {
    Optional<TagDTO> getTag(String id);

    void addTag(String id, String name);
}
