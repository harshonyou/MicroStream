package com.example;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@MicronautTest(startApplication = false, environments = "no-streams")
public class SubscriptionApplicationArgsTest {
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
//        assertDoesNotThrow(() -> SubscriptionApplication.main(new String[]{}));
//    }

    @Test
    public void testConstructor() {
        assertDoesNotThrow(SubscriptionApplication::new);
    }
}
