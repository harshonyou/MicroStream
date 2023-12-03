package com.example.video.messaging;

import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;

@Singleton
public class TestKafkaService {

        private final TestProducer testProducer;

        public TestKafkaService(TestProducer testProducer) {
            this.testProducer = testProducer;
        }

        @Scheduled(fixedDelay = "10s")
        public void sendFakeUpdate() {
            testProducer.sendFakeUpdate("Hello World!");
        }
}
