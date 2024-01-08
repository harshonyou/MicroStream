package com.example.mapper;

import com.example.dto.VideoEngagementDTO;
import com.example.model.UserEngagement;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false, environments = "no-streams")
class VideoEngagementMapperTest {
    @Test
    public void testFromDto() {
        VideoEngagementDTO dto = new VideoEngagementDTO();
        dto.setUserId("testUser");
        dto.setVideoId(UUID.randomUUID());
        dto.setWatchedTime(Instant.now());

        UserEngagement ue = VideoEngagementMapper.fromDto(dto);

        assertEquals(dto.getUserId(), ue.getUserId());
        assertEquals(dto.getVideoId(), ue.getVideoId());
        assertEquals(dto.getWatchedTime(), ue.getWatchedTime());
    }

    @Test
    public void testFromEntity() {
        UserEngagement ue = new UserEngagement();
        ue.setUserId("testUser");
        ue.setVideoId(UUID.randomUUID());
        ue.setWatchedTime(Instant.now());

        VideoEngagementDTO dto = VideoEngagementMapper.fromEntity(ue);

        assertEquals(ue.getUserId(), dto.getUserId());
        assertEquals(ue.getVideoId(), dto.getVideoId());
        assertEquals(ue.getWatchedTime(), dto.getWatchedTime());
    }

    @Test
    public void testVideoEngagementMapperConstructor() {
        // Ensure that the constructor is not null
        VideoEngagementMapper mapper = new VideoEngagementMapper();
        assertNotNull(mapper);
    }
}