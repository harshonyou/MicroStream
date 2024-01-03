package com.example.repository;

import com.example.model.Tag;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false, environments = "no-streams")
class Neo4jTagRepositoryTest {
    @Inject
    Neo4jTagRepository neo4jTagRepository;

    @AfterEach
    public void tearDown() {
        neo4jTagRepository.deleteAll();
    }

    @Test
    public void testFindByTagNameWithNonExistingTag() {
        assertFalse(neo4jTagRepository.findByTagName("non-existing-tag").isPresent());
    }

    @Test
    public void testAddTag() {
        String tagName = "tag-name";
        neo4jTagRepository.addTag(new Tag(tagName));

        Optional<Tag> tag = neo4jTagRepository.findByTagName(tagName);
        assertTrue(tag.isPresent());
        assertEquals(tagName, tag.get().getName());
    }

    @Test
    public void testAssociateUserWithTag() {
        String tagName = "tag-name";
        String userId = "user-id";
        neo4jTagRepository.addTag(new Tag(tagName));
        neo4jTagRepository.associateUserWithTag(tagName, userId);

        Optional<Tag> tag = neo4jTagRepository.findByTagName(tagName);
        assertTrue(tag.isPresent());
        assertEquals(tagName, tag.get().getName());
    }

    @Test
    public void testDisassociateUserFromTag() {
        String tagName = "tag-name";
        String userId = "user-id";
        neo4jTagRepository.addTag(new Tag(tagName));
        neo4jTagRepository.associateUserWithTag(tagName, userId);
        neo4jTagRepository.disassociateUserFromTag(tagName, userId);

        Optional<Tag> tag = neo4jTagRepository.findByTagName(tagName);
        assertTrue(tag.isPresent());
        assertEquals(tagName, tag.get().getName());
    }
}