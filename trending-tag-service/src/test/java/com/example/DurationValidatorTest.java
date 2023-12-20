package com.example;

import com.example.validator.DurationValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DurationValidatorTest {

    @Test
    void testValidDurationSingleUnit() {
        assertTrue(DurationValidator.isValidDuration("12h"), "12h should be valid");
        assertTrue(DurationValidator.isValidDuration("30m"), "30m should be valid");
        assertTrue(DurationValidator.isValidDuration("2d"), "2d should be valid");
    }

    @Test
    void testValidDurationMultipleUnits() {
        assertTrue(DurationValidator.isValidDuration("1d12h"), "1d12h should be valid");
        assertTrue(DurationValidator.isValidDuration("1d30m"), "1d30m should be valid");
        assertTrue(DurationValidator.isValidDuration("2h45m"), "2h45m should be valid");
        assertTrue(DurationValidator.isValidDuration("1d2h30m"), "1d2h30m should be valid");
    }

    @Test
    void testInvalidDurationFormat() {
        assertFalse(DurationValidator.isValidDuration("12"), "12 without unit should be invalid");
        assertFalse(DurationValidator.isValidDuration("abc"), "abc should be invalid");
        assertFalse(DurationValidator.isValidDuration("1e 2f"), "1e 2f should be invalid");
    }

    @Test
    void testInvalidDurationOrder() {
        assertFalse(DurationValidator.isValidDuration("30m1h"), "30m1h should be invalid (wrong order)");
        assertFalse(DurationValidator.isValidDuration("1h2d"), "1h2d should be invalid (wrong order)");
        assertFalse(DurationValidator.isValidDuration("45m1d"), "45m1d should be invalid (wrong order)");
    }

    @Test
    void testInvalidDurationRepeatUnits() {
        assertFalse(DurationValidator.isValidDuration("1h1h"), "1h1h should be invalid (repeated units)");
        assertFalse(DurationValidator.isValidDuration("2d3d"), "2d3d should be invalid (repeated units)");
        assertFalse(DurationValidator.isValidDuration("20m15m"), "20m15m should be invalid (repeated units)");
    }

    @Test
    void testInvalidDurationExcessiveValues() {
        assertFalse(DurationValidator.isValidDuration("1000d"), "1000d should be invalid (excessive days)");
        assertFalse(DurationValidator.isValidDuration("25h"), "25h should be invalid (excessive hours)");
        assertFalse(DurationValidator.isValidDuration("61m"), "61m should be invalid (excessive minutes)");
    }

    @Test
    void testEmptyOrNullDuration() {
        assertFalse(DurationValidator.isValidDuration(""), "Empty string should be invalid");
        assertFalse(DurationValidator.isValidDuration(null), "null should be invalid");
    }
}