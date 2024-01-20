package com.example.service;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.example.dto.VideoDTO;
import com.example.dto.VideoFeedbackDTO;
import com.example.mapper.VideoMapper;
import com.example.repository.CassandraVideoRepository;
import com.example.repository.CassandraVideoTagRepository;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false, environments = "no-streams")
class VideoFeedbackServiceImplTest {
    @Inject
    CassandraVideoRepository videoRepository;

    @Inject
    CassandraVideoTagRepository videoTagRepository;

    @Inject
    VideoFeedbackServiceImpl videoFeedbackService;

    @AfterEach
    public void tearDown() {
        videoRepository.deleteAll();
        videoTagRepository.deleteAll();
    }

    @Test
    public void testSubmitFeedback() {
        String userId = "user1";
        UUID videoId = Uuids.timeBased();
        boolean likeStatus = true;

        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setUserId(userId);
        videoDTO.setVideoId(videoId);
        Set<String> tags = new HashSet<>();
        tags.add("tag1");
        tags.add("tag2");
        videoDTO.setTags(tags);

        videoRepository.save(VideoMapper.fromDto(videoDTO));

        Optional<VideoFeedbackDTO> result = videoFeedbackService.submitFeedback(userId, videoId, likeStatus);

        assertTrue(result.isPresent());
        assertEquals(userId, result.get().getUserId());
        assertEquals(videoId, result.get().getVideoId());
        assertEquals(likeStatus, result.get().isLikeStatus());

        videoId = Uuids.timeBased();
        result = videoFeedbackService.submitFeedback(userId, videoId, likeStatus);

        assertFalse(result.isPresent());
    }
}