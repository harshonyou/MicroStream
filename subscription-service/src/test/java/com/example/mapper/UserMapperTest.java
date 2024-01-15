package com.example.mapper;

import com.example.dto.UserDTO;
import com.example.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    @Test
    public void testClassExists() {
        UserMapper userMapper = new UserMapper();
        assertNotNull(userMapper, "UserMapper class should exist");
    }

    @Test
    public void testFromDTO() {
        UserDTO userDTO = new UserDTO("123", "John Doe");
        User user = UserMapper.fromDTO(userDTO);

        assertEquals(userDTO.getId(), user.getId(), "Mapping from DTO to User failed");
        assertEquals(userDTO.getName(), user.getName(), "Mapping from DTO to User failed");
    }

    @Test
    public void testFromEntity() {
        User user = new User();
        user.setId("123");
        user.setName("John Doe");
        UserDTO userDTO = UserMapper.fromEntity(user);

        assertEquals(user.getId(), userDTO.getId(), "Mapping from User to DTO failed");
        assertEquals(user.getName(), userDTO.getName(), "Mapping from User to DTO failed");
    }
}