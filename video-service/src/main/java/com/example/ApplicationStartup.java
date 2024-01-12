package com.example;

import com.datastax.oss.driver.api.core.CqlSession;
import com.example.repository.*;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.discovery.event.ServiceReadyEvent;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class ApplicationStartup implements ApplicationEventListener<ServiceReadyEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationStartup.class);

    private final CqlSession cqlSession;

    public ApplicationStartup(CqlSession cqlSession) {
        this.cqlSession = cqlSession;
    }

    public void onApplicationEvent(ServiceReadyEvent event) {
        LOGGER.info("Startup Initialization");
        CassandraVideoRepository.createTableVideo(cqlSession);
        LOGGER.info("+ Table VideoItems created if needed.");
        CassandraVideoEngagementRepository.createTableUserVideoWatch(cqlSession);
        LOGGER.info("+ Table UserVideoWatch created if needed.");
        CassandraVideoTagRepository.createTableTag(cqlSession);
        LOGGER.info("+ Table VideoByHashtag created if needed.");
        LOGGER.info("[OK]");
    }
}
