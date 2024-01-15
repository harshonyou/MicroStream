package com.example.util;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false, environments = "no-streams")
class IntervalConverterTest {

    @Test
    void testClassExists() {
        IntervalConverter intervalConverter = new IntervalConverter();
        assertNotNull(intervalConverter);
    }

    @Test
    void testValidInputDays() {
        assertEquals("10 day", IntervalConverter.convertToPostgresInterval("10d"));
    }

    @Test
    void testValidInputHours() {
        assertEquals("5 hour", IntervalConverter.convertToPostgresInterval("5h"));
    }

    @Test
    void testValidInputMinutes() {
        assertEquals("30 minute", IntervalConverter.convertToPostgresInterval("30m"));
    }

    @Test
    void testValidInputMixed() {
        assertEquals("2 day 3 hour 45 minute", IntervalConverter.convertToPostgresInterval("2d3h45m"));
    }

    @Test
    void testInvalidInputLetters() {
        assertEquals("", IntervalConverter.convertToPostgresInterval("abc"));
    }

    @Test
    void testInvalidInputFormat() {
        assertEquals("", IntervalConverter.convertToPostgresInterval("10x20y"));
    }

    @Test
    void testEmptyString() {
        assertEquals("", IntervalConverter.convertToPostgresInterval(""));
    }

    @Test
    void testVeryLongString() {
        String longString = "100000d100000h100000m";
        assertEquals("100000 day 100000 hour 100000 minute", IntervalConverter.convertToPostgresInterval(longString));
    }

    @Test
    void testNullInput() {
        assertThrows(NullPointerException.class, () -> IntervalConverter.convertToPostgresInterval(null));
    }

    @Test
    void testInputWithSpaces() {
        assertEquals("1 day 1 hour 1 minute", IntervalConverter.convertToPostgresInterval(" 1d 1h 1m "));
    }

    @Test
    void testCaseSensitivity() {
        assertEquals("", IntervalConverter.convertToPostgresInterval("1D2H3M"));
    }
}