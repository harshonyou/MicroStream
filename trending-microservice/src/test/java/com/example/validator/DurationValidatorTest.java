package com.example.validator;

import com.example.validator.DurationValidator;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false, environments = "no-streams")
class DurationValidatorTest {

    @Test
    void testClassExists() {
        DurationValidator durationValidator = new DurationValidator();
        assertNotNull(durationValidator);
    }

    @Test
    void testValidDurations() {
        assertTrue(DurationValidator.isValidDuration("10d"));
        assertTrue(DurationValidator.isValidDuration("5h"));
        assertTrue(DurationValidator.isValidDuration("30m"));
        assertTrue(DurationValidator.isValidDuration("10d5h30m"));
    }

    @Test
    void testInvalidDurations() {
        assertFalse(DurationValidator.isValidDuration("-1d"));
        assertFalse(DurationValidator.isValidDuration("25h"));
        assertFalse(DurationValidator.isValidDuration("60m"));
        assertFalse(DurationValidator.isValidDuration("10h5d"));
    }

    @Test
    void testBoundaryConditions() {
        assertTrue(DurationValidator.isValidDuration("0d"));
        assertTrue(DurationValidator.isValidDuration("365d"));
        assertTrue(DurationValidator.isValidDuration("0h"));
        assertTrue(DurationValidator.isValidDuration("23h"));
        assertTrue(DurationValidator.isValidDuration("0m"));
        assertTrue(DurationValidator.isValidDuration("59m"));
    }

    @Test
    void testSpecialCases() {
        assertFalse(DurationValidator.isValidDuration(null));
        assertFalse(DurationValidator.isValidDuration(""));
        assertFalse(DurationValidator.isValidDuration("    "));
    }

    @Test
    void testFormatErrors() {
        assertFalse(DurationValidator.isValidDuration("10"));
        assertFalse(DurationValidator.isValidDuration("dhm"));
        assertFalse(DurationValidator.isValidDuration("10x"));
    }

    @Test
    void testSequentialOrder() {
        assertFalse(DurationValidator.isValidDuration("5h10d"));
        assertFalse(DurationValidator.isValidDuration("30m5h"));
    }
}