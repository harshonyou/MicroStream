package com.example.video;

import com.datastax.oss.driver.api.core.CqlSession;
import com.example.video.repository.CassandraUserVideoWatchRepository;
import com.example.video.repository.CassandraVideoRepository;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.discovery.event.ServiceReadyEvent;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class ApplicationStartup implements ApplicationEventListener<ServiceReadyEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationStartup.class);

    @Inject
    private CqlSession cqlSession;

    @Override
    public void onApplicationEvent(ServiceReadyEvent event) {
        LOGGER.info("Startup Initialization");
        CassandraVideoRepository.createTableVideo(cqlSession);
        LOGGER.info("+ Table VideoItems created if needed.");
        CassandraUserVideoWatchRepository.createTableUserVideoWatch(cqlSession);
        LOGGER.info("+ Table UserVideoWatch created if needed.");
        LOGGER.info("[OK]");
    }
}
