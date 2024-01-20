package com.example.repository;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.example.model.UserEngagement;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false, environments = "no-streams")
class CassandraVideoEngagementRepositoryTest {
    @Inject
    CassandraVideoEngagementRepository  videoEngagementRepository;

    @AfterEach
    public void tearDown() {
        videoEngagementRepository.deleteAll();
    }

    @Test
    public void testSaveUserEngagement() {
        UserEngagement userEngagement1 = new UserEngagement();
        userEngagement1.setUserId("testUser1");
        userEngagement1.setVideoId(Uuids.timeBased());
        userEngagement1.setWatchedTime(Instant.now());

        UserEngagement userEngagement2 = new UserEngagement();
        userEngagement2.setUserId("testUser2");
        userEngagement2.setVideoId(Uuids.timeBased());

        UserEngagement savedUserEngagement1 = videoEngagementRepository.save(userEngagement1);
        UserEngagement savedUserEngagement2 = videoEngagementRepository.save(userEngagement2);

        assertNotNull(savedUserEngagement1.getWatchedTime());
        assertEquals("testUser1", savedUserEngagement1.getUserId());
        assertNotNull(savedUserEngagement1.getVideoId());

        assertNotNull(savedUserEngagement2.getWatchedTime());
        assertEquals("testUser2", savedUserEngagement2.getUserId());
        assertNotNull(savedUserEngagement2.getVideoId());
    }

    @Test
    public void testFindByUser() {
        UserEngagement userEngagement1 = new UserEngagement();
        userEngagement1.setUserId("testUser1");
        userEngagement1.setVideoId(Uuids.timeBased());
        userEngagement1.setWatchedTime(Instant.now());

        UserEngagement userEngagement2 = new UserEngagement();
        userEngagement2.setUserId("testUser1");
        userEngagement2.setVideoId(Uuids.timeBased());
        userEngagement2.setWatchedTime(Instant.now());

        videoEngagementRepository.save(userEngagement1);
        videoEngagementRepository.save(userEngagement2);

        List<UserEngagement> userEngagements = videoEngagementRepository.findByUser("testUser1");

        assertEquals(2, userEngagements.size());
        assertEquals("testUser1", userEngagements.get(0).getUserId());
        assertEquals("testUser1", userEngagements.get(1).getUserId());
    }

    @Test
    public void testFindById() {
        UserEngagement userEngagement = new UserEngagement();
        userEngagement.setUserId("testUser");
        userEngagement.setVideoId(Uuids.timeBased());
        userEngagement.setWatchedTime(Instant.now());

        UserEngagement savedUserEngagement = videoEngagementRepository.save(userEngagement);

        Optional<UserEngagement> foundUserEngagement = videoEngagementRepository.findById("testUser", savedUserEngagement.getVideoId());
        Optional<UserEngagement> foundNonUserEngagement = videoEngagementRepository.findById("non-existing-testUser", savedUserEngagement.getVideoId());

        assertTrue(foundUserEngagement.isPresent());
        assertEquals(savedUserEngagement.getUserId(), foundUserEngagement.get().getUserId());
        assertEquals(savedUserEngagement.getVideoId(), foundUserEngagement.get().getVideoId());

        assertTrue(foundNonUserEngagement.isEmpty());
    }

    @Test
    public void testDeleteAllUserEngagements() {
        UserEngagement userEngagement1 = new UserEngagement();
        userEngagement1.setUserId("testUser1");
        userEngagement1.setVideoId(Uuids.timeBased());
        userEngagement1.setWatchedTime(Instant.now());

        UserEngagement userEngagement2 = new UserEngagement();
        userEngagement2.setUserId("testUser2");
        userEngagement2.setVideoId(Uuids.timeBased());
        userEngagement2.setWatchedTime(Instant.now());

        videoEngagementRepository.save(userEngagement1);
        videoEngagementRepository.save(userEngagement2);

        videoEngagementRepository.deleteAll();

        List<UserEngagement> allUserEngagements = videoEngagementRepository.findByUser("testUser1");

        assertEquals(0, allUserEngagements.size());
    }
}

