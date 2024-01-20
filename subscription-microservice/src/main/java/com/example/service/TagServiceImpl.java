package com.example.service;

import com.example.dto.TagDTO;
import com.example.mapper.TagMapper;
import com.example.model.Tag;
import com.example.repository.TagRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.Set;

@Singleton
public class TagServiceImpl implements TagService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TagServiceImpl.class);

    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public void addTag(String tagName) {
        LOGGER.info("Adding tag: {}", tagName);

        if(findByTagName(tagName).isPresent()) {
            LOGGER.info("Tag: {} already exists", tagName);
            return;
        }

        tagRepository.addTag(new Tag(tagName));
    }

    @Override
    public void addTags(Set<String> tagNames) {
        for(String tagName : tagNames)
            addTag(tagName);
    }

    @Override
    public Optional<TagDTO> findByTagName(String tagName) {
        LOGGER.info("Finding tag by name: {}", tagName);
        Optional<Tag> tag = tagRepository.findByTagName(tagName);

        return tag.map(TagMapper::fromEntity);
    }
}
