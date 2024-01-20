package com.example.repository;

import com.example.model.User;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@MicronautTest(transactional = false, environments = "no-streams")
class Neo4jUserRepositoryTest {

    @Inject
    Neo4jUserRepository neo4jUserRepository;

    @AfterEach
    public void tearDown() {
        neo4jUserRepository.deleteAll();
    }

    @Test
    public void testFindByIdWithNonExistingId() {
        assertFalse(neo4jUserRepository.findById("non-existing-id").isPresent());
    }

    @Test
    public void testAddUser() {
        String userId = "user-id";
        String userName = "user-name";
        neo4jUserRepository.addUser(new User(userId, userName));

        Optional<User> user = neo4jUserRepository.findById(userId);
        assertTrue(user.isPresent());
        assertEquals(userId, user.get().getId());
        assertEquals(userName, user.get().getName());
    }
}