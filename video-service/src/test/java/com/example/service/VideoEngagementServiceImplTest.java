package com.example.service;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.example.dto.VideoDTO;
import com.example.dto.VideoEngagementDTO;
import com.example.mapper.VideoMapper;
import com.example.model.UserEngagement;
import com.example.repository.CassandraVideoEngagementRepository;
import com.example.repository.CassandraVideoRepository;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false, environments = "no-streams")
class VideoEngagementServiceImplTest {
    @Inject
    CassandraVideoRepository videoRepository;
    @Inject
    CassandraVideoEngagementRepository videoEngagementRepository;

    @Inject
    VideoEngagementServiceImpl videoEngagementService;

    @AfterEach
    public void tearDown() {
        videoRepository.deleteAll();
        videoEngagementRepository.deleteAll();
    }

    @Test
    public void testMarkVideoWatched() {
        VideoEngagementDTO inputDto = new VideoEngagementDTO();
        inputDto.setUserId("user1");
        inputDto.setVideoId(Uuids.timeBased());
        inputDto.setWatchedTime(Instant.now());

        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setUserId("user1");
        videoDTO.setVideoId(inputDto.getVideoId());
        videoDTO.setTitle("title");

        videoRepository.save(VideoMapper.fromDto(videoDTO));

        Optional<VideoEngagementDTO> eventDtoList = videoEngagementService.markVideoWatched(inputDto);

        assertTrue(eventDtoList.isPresent());
        assertEquals(inputDto.getUserId(), eventDtoList.get().getUserId());
        assertEquals(inputDto.getVideoId(), eventDtoList.get().getVideoId());
        assertEquals(inputDto.getWatchedTime(), eventDtoList.get().getWatchedTime());

        inputDto.setUserId("user2");
        inputDto.setVideoId(Uuids.timeBased());
        Optional<VideoEngagementDTO> nonExistingEventDtoList = videoEngagementService.markVideoWatched(inputDto);

        assertTrue(nonExistingEventDtoList.isEmpty());
    }

    @Test
    public void testFindWatchStatus() {
        String userId = "user1";
        UUID videoId = Uuids.timeBased();

        UserEngagement userEngagement = new UserEngagement();
        userEngagement.setUserId(userId);
        userEngagement.setVideoId(videoId);
        userEngagement.setWatchedTime(Instant.now());

        videoEngagementRepository.save(userEngagement);

        Optional<VideoEngagementDTO> result = videoEngagementService.findWatchStatus(userId, videoId);

        assertTrue(result.isPresent());
        assertEquals(userEngagement.getUserId(), result.get().getUserId());
        assertEquals(userEngagement.getVideoId(), result.get().getVideoId());
        assertNotNull(result.get().getWatchedTime());

        result = videoEngagementService.findWatchStatus("user2", videoId);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindWatchHistory() {
        String userId = "user1";

        UserEngagement userEngagement1 = new UserEngagement();
        userEngagement1.setUserId(userId);
        userEngagement1.setVideoId(Uuids.timeBased());
        userEngagement1.setWatchedTime(Instant.now());

        UserEngagement userEngagement2 = new UserEngagement();
        userEngagement2.setUserId(userId);
        userEngagement2.setVideoId(Uuids.timeBased());
        userEngagement2.setWatchedTime(Instant.now());

        videoEngagementRepository.save(userEngagement1);
        videoEngagementRepository.save(userEngagement2);

        List<VideoEngagementDTO> result = videoEngagementService.findWatchHistory(userId);

        assertEquals(2, result.size());
        assertEquals(userId, result.get(0).getUserId());
        assertEquals(userId, result.get(1).getUserId());
    }
}


