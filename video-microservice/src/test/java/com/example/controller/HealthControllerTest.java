// Code Generated Via EGL Template

package com.example.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(environments = "no-streams")
class HealthControllerTest {

    HealthController healthController;

    @BeforeEach
    public void setUp() {
        healthController = new HealthController();
    }

    @Test
    public void testHealth() {
        HttpResponse<String> response = healthController.health();

        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals("OK", response.getBody().get());
    }
}