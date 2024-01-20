package com.example.controller;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.example.dto.VideoDTO;
import com.example.service.VideoService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@MicronautTest(environments = "no-streams")
class VideoControllerTest {
    @Mock
    VideoService mockVideoService;

    VideoController videoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        videoController = new VideoController(mockVideoService);
    }

    @Test
    public void testCreateVideo() {
        String userId = "user1";
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setUserId(userId);

        when(mockVideoService.fetch(userId, videoDTO.getVideoId())).thenReturn(Optional.empty());
        when(mockVideoService.post(videoDTO)).thenReturn(videoDTO);

        HttpResponse<VideoDTO> response = videoController.create(userId, videoDTO);

        assertEquals(HttpStatus.CREATED, response.getStatus());
        assertEquals(videoDTO, response.getBody().get());
    }

    @Test
    public void testCreateVideoWithID() {
        String userId = "user1";
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setUserId(userId);
        videoDTO.setVideoId(Uuids.timeBased());

        when(mockVideoService.fetch(userId, videoDTO.getVideoId())).thenReturn(Optional.empty());
        when(mockVideoService.post(videoDTO)).thenReturn(videoDTO);

        HttpResponse<VideoDTO> response = videoController.create(userId, videoDTO);

        assertEquals(HttpStatus.CREATED, response.getStatus());
        assertEquals(videoDTO, response.getBody().get());
    }

    @Test
    public void testCreateVideoBadRequest() {
        String userId = "user1";
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setUserId(userId);
        videoDTO.setVideoId(Uuids.timeBased());

        when(mockVideoService.fetch(userId, videoDTO.getVideoId())).thenReturn(Optional.of(videoDTO));

        HttpResponse<VideoDTO> response = videoController.create(userId, videoDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatus());
    }

    @Test
    public void testUpdateVideo() {
        String userId = "user1";
        String videoId = UUID.randomUUID().toString();
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setUserId(userId);
        videoDTO.setVideoId(UUID.fromString(videoId));

        when(mockVideoService.fetch(userId, UUID.fromString(videoId))).thenReturn(Optional.of(videoDTO));
        when(mockVideoService.post(videoDTO)).thenReturn(videoDTO);

        HttpResponse<VideoDTO> response = videoController.update(userId, videoId, videoDTO);

        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals(videoDTO, response.getBody().get());
    }

    @Test
    public void testUpdateVideoNotFound() {
        String userId = "user1";
        String videoId = UUID.randomUUID().toString();
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setUserId(userId);
        videoDTO.setVideoId(UUID.fromString(videoId));

        when(mockVideoService.fetch(userId, UUID.fromString(videoId))).thenReturn(Optional.empty());

        HttpResponse<VideoDTO> response = videoController.update(userId, videoId, videoDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
    }

    @Test
    public void testFindById() {
        String userId = "user1";
        String videoId = UUID.randomUUID().toString();
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setUserId(userId);
        videoDTO.setVideoId(UUID.fromString(videoId));

        when(mockVideoService.fetch(userId, UUID.fromString(videoId))).thenReturn(Optional.of(videoDTO));

        HttpResponse<VideoDTO> response = videoController.findById(userId, videoId);

        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals(videoDTO, response.getBody().get());
    }

    @Test
    public void testFindByIdNotFound() {
        String userId = "user1";
        String videoId = UUID.randomUUID().toString();

        when(mockVideoService.fetch(userId, UUID.fromString(videoId))).thenReturn(Optional.empty());

        HttpResponse<VideoDTO> response = videoController.findById(userId, videoId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
    }

    @Test
    public void testFindAllByUser() {
        String userId = "user1";
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setUserId(userId);

        when(mockVideoService.getUserPosts(userId)).thenReturn(List.of(videoDTO));

        HttpResponse<Iterable<VideoDTO>> response = videoController.findAllByUser(userId);

        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals(List.of(videoDTO), response.getBody().get());
    }

    @Test
    public void testFindAllByUserNotFound() {
        String userId = "user1";

        when(mockVideoService.getUserPosts(userId)).thenReturn(List.of());

        HttpResponse<Iterable<VideoDTO>> response = videoController.findAllByUser(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
    }

    @Test
    public void testDeleteById() {
        String userId = "user1";
        String videoId = UUID.randomUUID().toString();
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setUserId(userId);
        videoDTO.setVideoId(UUID.fromString(videoId));

        when(mockVideoService.fetch(userId, UUID.fromString(videoId))).thenReturn(Optional.of(videoDTO));

        HttpResponse<Void> response = videoController.deleteById(userId, videoId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
    }

    @Test
    public void testDeleteByIdNotFound() {
        String userId = "user1";
        String videoId = UUID.randomUUID().toString();

        when(mockVideoService.fetch(userId, UUID.fromString(videoId))).thenReturn(Optional.empty());

        HttpResponse<Void> response = videoController.deleteById(userId, videoId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
    }   

    @Test
    public void testDeleteAllByUser() {
        String userId = "user1";

        HttpResponse<Void> response = videoController.deleteAllByUser(userId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
    }
}

