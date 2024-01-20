package com.example.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagDTOTest {
    @Test
    public void testSetNameAndGetName() {
        TagDTO tagDTO = new TagDTO();
        String testName = "Sample Tag";

        tagDTO.setName(testName);
        assertEquals(testName, tagDTO.getName(), "setName or getName does not work correctly");
    }

    @Test
    public void testNoArgsConstructor() {
        TagDTO tagDTO = new TagDTO();
        assertNull(tagDTO.getName(), "NoArgsConstructor should initialize name as null");
    }

    @Test
    public void testAllArgsConstructor() {
        String testName = "Sample Tag";
        TagDTO tagDTO = new TagDTO(testName);

        assertEquals(testName, tagDTO.getName(), "AllArgsConstructor does not set name correctly");
    }
}