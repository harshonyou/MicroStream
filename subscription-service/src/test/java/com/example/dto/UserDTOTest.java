package com.example.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {
    @Test
    public void testSetAndGetId() {
        UserDTO userDTO = new UserDTO();
        String testId = "12345";

        userDTO.setId(testId);
        assertEquals(testId, userDTO.getId(), "setId or getId does not work correctly");
    }

    @Test
    public void testSetAndGetName() {
        UserDTO userDTO = new UserDTO();
        String testName = "John Doe";

        userDTO.setName(testName);
        assertEquals(testName, userDTO.getName(), "setName or getName does not work correctly");
    }

    @Test
    public void testNoArgsConstructor() {
        UserDTO userDTO = new UserDTO();
        assertNull(userDTO.getId(), "NoArgsConstructor should initialize id as null");
        assertNull(userDTO.getName(), "NoArgsConstructor should initialize name as null");
    }

    @Test
    public void testAllArgsConstructor() {
        String testId = "12345";
        String testName = "John Doe";
        UserDTO userDTO = new UserDTO(testId, testName);

        assertEquals(testId, userDTO.getId(), "AllArgsConstructor does not set id correctly");
        assertEquals(testName, userDTO.getName(), "AllArgsConstructor does not set name correctly");
    }
}