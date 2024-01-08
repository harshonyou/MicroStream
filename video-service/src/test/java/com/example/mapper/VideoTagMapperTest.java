package com.example.mapper;

import com.example.dto.VideoTagDTO;
import com.example.model.VideoTag;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false, environments = "no-streams")
class VideoTagMapperTest {
    @Test
    public void testFromDto() {
        VideoTagDTO dto = new VideoTagDTO();
        dto.setTag("TestTag");
        dto.setVideoId(UUID.randomUUID());

        VideoTag v = VideoTagMapper.fromDto(dto);

        assertEquals(dto.getTag(), v.getTag());
        assertEquals(dto.getVideoId(), v.getVideoId());
    }

    @Test
    public void testFromEntity() {
        VideoTag v = new VideoTag();
        v.setTag("TestTag");
        v.setVideoId(UUID.randomUUID());

        VideoTagDTO dto = VideoTagMapper.fromEntity(v);

        assertEquals(v.getTag(), dto.getTag());
        assertEquals(v.getVideoId(), dto.getVideoId());
    }

    @Test
    public void testVideoTagMapperConstructor() {
        // Ensure that the constructor is not null
        VideoTagMapper mapper = new VideoTagMapper();
        assertNotNull(mapper);
    }
}