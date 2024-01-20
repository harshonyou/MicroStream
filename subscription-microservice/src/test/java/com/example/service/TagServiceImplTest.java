package com.example.service;

import com.example.dto.TagDTO;
import com.example.model.Tag;
import com.example.repository.Neo4jTagRepository;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false, environments = "no-streams")
class TagServiceImplTest {
    @Inject
    Neo4jTagRepository tagRepository;

    @Inject
    TagServiceImpl tagService;

    @AfterEach
    public void tearDown() {
        tagRepository.deleteAll();
    }

    @Test
    public void testFindByTagNameWithNonExistingTag() {
        assertFalse(tagService.findByTagName("non-existing-tag").isPresent());
    }

    @Test
    public void testAddTag() {
        String tagName = "tag-name";
        tagService.addTag(tagName);

        Optional<TagDTO> tagDTO = tagService.findByTagName(tagName);
        assertTrue(tagDTO.isPresent());
        assertEquals(tagName, tagDTO.get().getName());

        Optional<Tag> tag = tagRepository.findByTagName(tagName);
        assertTrue(tag.isPresent());
        assertEquals(tagName, tag.get().getName());
    }

    @Test
    public void testAddTagWithExistingTag() {
        String tagName = "tag-name";
        tagService.addTag(tagName);
        tagService.addTag(tagName);

        Optional<TagDTO> tagDTO = tagService.findByTagName(tagName);
        assertTrue(tagDTO.isPresent());
        assertEquals(tagName, tagDTO.get().getName());

        Optional<Tag> tag = tagRepository.findByTagName(tagName);
        assertTrue(tag.isPresent());
        assertEquals(tagName, tag.get().getName());
    }

    @Test
    public void testAddTags() {
        String tagName1 = "tag-name-1";
        String tagName2 = "tag-name-2";
        tagService.addTags(Set.of(tagName1, tagName2));

        Optional<TagDTO> tagDTO1 = tagService.findByTagName(tagName1);
        assertTrue(tagDTO1.isPresent());
        assertEquals(tagName1, tagDTO1.get().getName());

        Optional<TagDTO> tagDTO2 = tagService.findByTagName(tagName2);
        assertTrue(tagDTO2.isPresent());
        assertEquals(tagName2, tagDTO2.get().getName());

        Optional<Tag> tag1 = tagRepository.findByTagName(tagName1);
        assertTrue(tag1.isPresent());
        assertEquals(tagName1, tag1.get().getName());

        Optional<Tag> tag2 = tagRepository.findByTagName(tagName2);
        assertTrue(tag2.isPresent());
        assertEquals(tagName2, tag2.get().getName());
    }
}