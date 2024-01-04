package com.example.video;

import com.datastax.oss.driver.api.core.CqlSession;
import com.example.video.repository.CassandraVideoEngagementRepository;
import com.example.video.repository.CassandraVideoTagRepository;
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

    private final CassandraVideoRepository videoRepository;

    private final CassandraVideoEngagementRepository videoEngagementRepository;

    private final CassandraVideoTagRepository videoTagRepository;

    public ApplicationStartup(CqlSession cqlSession) {
        this.videoRepository = new CassandraVideoRepository(cqlSession);
        this.videoEngagementRepository = new CassandraVideoEngagementRepository(cqlSession);
        this.videoTagRepository = new CassandraVideoTagRepository(cqlSession);
    }

    @Override
    public void onApplicationEvent(ServiceReadyEvent event) {
        LOGGER.info("Startup Initialization");
        videoRepository.createTableVideo();
        LOGGER.info("+ Table VideoItems created if needed.");
        videoEngagementRepository.createTableUserVideoWatch();
        LOGGER.info("+ Table UserVideoWatch created if needed.");
        videoTagRepository.createTableTag();
        LOGGER.info("+ Table VideoByHashtag created if needed.");
        LOGGER.info("[OK]");
    }
}
