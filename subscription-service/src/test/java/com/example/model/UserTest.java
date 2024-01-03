package com.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    public void testSetAndGetId() {
        User user = new User();
        String testId = "12345";

        user.setId(testId);
        assertEquals(testId, user.getId(), "setId or getId does not work correctly");
    }

    @Test
    public void testSetAndGetName() {
        User user = new User();
        String testName = "John Doe";

        user.setName(testName);
        assertEquals(testName, user.getName(), "setName or getName does not work correctly");
    }

    @Test
    public void testNoArgsConstructor() {
        User user = new User();
        assertNull(user.getId(), "NoArgsConstructor should initialize id as null");
        assertNull(user.getName(), "NoArgsConstructor should initialize name as null");
    }

    @Test
    public void testAllArgsConstructor() {
        String testId = "12345";
        String testName = "John Doe";
        User user = new User(testId, testName);

        assertEquals(testId, user.getId(), "AllArgsConstructor does not set id correctly");
        assertEquals(testName, user.getName(), "AllArgsConstructor does not set name correctly");
    }
}