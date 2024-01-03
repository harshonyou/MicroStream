package com.example.model;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false, environments = "no-streams")
class TagTest {
    @Test
    public void testSetNameAndGetName() {
        Tag tag = new Tag();
        String testName = "Sample Tag";

        tag.setName(testName);
        assertEquals(testName, tag.getName(), "setName or getName does not work correctly");
    }

    @Test
    public void testNoArgsConstructor() {
        Tag tag = new Tag();
        assertNull(tag.getName(), "NoArgsConstructor should initialize name as null");
    }

    @Test
    public void testAllArgsConstructor() {
        String testName = "Sample Tag";
        Tag tag = new Tag(testName);

        assertEquals(testName, tag.getName(), "AllArgsConstructor does not set name correctly");
    }
}