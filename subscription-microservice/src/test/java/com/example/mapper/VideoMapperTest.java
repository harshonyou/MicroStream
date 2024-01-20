package com.example.mapper;

import com.example.dto.VideoDTO;
import com.example.model.Video;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class VideoMapperTest {

    @Test
    public void testClassExists() {
        VideoMapper videoMapper = new VideoMapper();
        assertNotNull(videoMapper, "VideoMapper class should exist");
    }

    @Test
    public void testFromDTO() {
        UUID videoId = UUID.randomUUID();
        VideoDTO videoDTO = new VideoDTO(videoId, "Test Video", 1000L);
        Video video = VideoMapper.fromDTO(videoDTO);

        assertEquals(videoDTO.getId(), video.getId(), "Mapping from DTO to Video failed");
        assertEquals(videoDTO.getTitle(), video.getTitle(), "Mapping from DTO to Video failed");
        assertEquals(videoDTO.getViews(), video.getViews(), "Mapping from DTO to Video failed");
    }

    @Test
    public void testFromEntity() {
        UUID videoId = UUID.randomUUID();
        Video video = new Video();
        video.setId(videoId);
        video.setTitle("Test Video");
        video.setViews(1000L);
        VideoDTO videoDTO = VideoMapper.fromEntity(video);

        assertEquals(video.getId(), videoDTO.getId(), "Mapping from Video to DTO failed");
        assertEquals(video.getTitle(), videoDTO.getTitle(), "Mapping from Video to DTO failed");
        assertEquals(video.getViews(), videoDTO.getViews(), "Mapping from Video to DTO failed");
    }
}