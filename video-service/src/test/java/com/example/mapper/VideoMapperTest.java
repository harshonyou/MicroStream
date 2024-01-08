package com.example.mapper;

import com.example.dto.VideoDTO;
import com.example.model.Video;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false, environments = "no-streams")
class VideoMapperTest {
    @Test
    public void testFromDto() {
        VideoDTO dto = new VideoDTO();
        dto.setUserId("testUser");
        dto.setVideoId(UUID.randomUUID());
        dto.setTitle("Test Video");
        Set<String> tags = new HashSet<>();
        tags.add("Tag1");
        tags.add("Tag2");
        dto.setTags(tags);

        Video v = VideoMapper.fromDto(dto);

        assertEquals(dto.getUserId(), v.getUserId());
        assertEquals(dto.getVideoId(), v.getVideoId());
        assertEquals(dto.getTitle(), v.getTitle());
        assertEquals(dto.getTags(), v.getTags());
    }

    @Test
    public void testFromEntity() {
        Video v = new Video();
        v.setUserId("testUser");
        v.setVideoId(UUID.randomUUID());
        v.setTitle("Test Video");
        Set<String> tags = new HashSet<>();
        tags.add("Tag1");
        tags.add("Tag2");
        v.setTags(tags);

        String user = "testUser";
        VideoDTO dto = VideoMapper.fromEntity(v, user);

        assertEquals(user, dto.getUserId());
        assertEquals(v.getVideoId(), dto.getVideoId());
        assertEquals(v.getTitle(), dto.getTitle());
        assertEquals(v.getTags(), dto.getTags());
    }

    @Test
    public void testVideoMapperConstructor() {
        // Ensure that the constructor is not null
        VideoMapper mapper = new VideoMapper();
        assertNotNull(mapper);
    }
}