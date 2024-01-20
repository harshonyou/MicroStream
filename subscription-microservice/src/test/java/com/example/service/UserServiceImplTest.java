package com.example.service;

import com.example.dto.UserDTO;
import com.example.model.User;
import com.example.repository.Neo4jUserRepository;
import com.example.repository.UserRepository;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false, environments = "no-streams")
class UserServiceImplTest {
    @Inject
    Neo4jUserRepository userRepository;

    @Inject
    UserServiceImpl userService;

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void testFindByIdWithNonExistingId() {
        assertFalse(userService.findById("non-existing-id").isPresent());
    }

    @Test
    public void testAddUser() {
        String userId = "user-id";
        String userName = "user-name";
        userService.addUser(userId, userName);

        Optional<UserDTO> user = userService.findById(userId);
        assertTrue(user.isPresent());
        assertEquals(userId, user.get().getId());
        assertEquals(userName, user.get().getName());
    }

    @Test
    public void testAddExistingUser() {
        String userId = "user-id";
        String userName = "user-name";
        userService.addUser(userId, userName);
        userService.addUser(userId, userName);

        Optional<UserDTO> user = userService.findById(userId);
        assertTrue(user.isPresent());
        assertEquals(userId, user.get().getId());
        assertEquals(userName, user.get().getName());
    }
}