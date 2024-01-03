package com.example.service;

import com.example.dto.TagDTO;
import com.example.mapper.TagMapper;
import com.example.model.Tag;
import com.example.repository.TagRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Optional;
import java.util.Set;

@Singleton
public class TagServiceImpl implements TagService {
    @Inject
    private TagRepository tagRepository;

    @Override
    public void addTag(String tagName) {
        if(findByTagName(tagName).isPresent())
            return;

        tagRepository.addTag(new Tag(tagName));
    }

    @Override
    public void addTags(Set<String> tagNames) {
        for(String tagName : tagNames)
            addTag(tagName);
    }

    @Override
    public Optional<TagDTO> findByTagName(String tagName) {
        Optional<Tag> tag = tagRepository.findByTagName(tagName);

        return tag.map(TagMapper::fromEntity);
    }
}
