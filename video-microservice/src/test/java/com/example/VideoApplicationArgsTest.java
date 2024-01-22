package com.example;

import io.micronaut.context.ApplicationContext;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@MicronautTest(startApplication = false, environments = "no-streams")
public class VideoApplicationArgsTest {
//    private ApplicationContext context;
//
//    @BeforeEach
//    public void setUp() {
//        context = ApplicationContext.run();
//    }
//
//    @AfterEach
//    public void tearDown() {
//        if (context != null) {
//            context.close();
//        }
//    }
//
//    @Test
//    public void testMain() {
//        assertDoesNotThrow(() -> VideoApplication.main(new String[]{}));
//    }

    @Test
    public void testConstructor() {
        assertDoesNotThrow(VideoApplication::new);
    }
}
