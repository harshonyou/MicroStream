package com.example.service;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.example.dto.VideoTagDTO;
import com.example.model.VideoTag;
import com.example.repository.CassandraVideoTagRepository;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false, environments = "no-streams")
class VideoTagServiceImplTest {
    @Inject
    CassandraVideoTagRepository videoTagRepository;

    @Inject
    VideoTagServiceImpl videoTagService;

    @AfterEach
    public void tearDown() {
        videoTagRepository.deleteAll();
    }

    @Test
    public void testTagVideo() {
        Set<String> tags = new HashSet<>();
        tags.add("tag1");
        tags.add("tag2");
        UUID videoId = Uuids.timeBased();

        videoTagService.tagVideo(tags, videoId);

        List<VideoTag> videoTags = videoTagRepository.findByTag("tag1");

        assertEquals(1, videoTags.size());
        assertEquals("tag1", videoTags.get(0).getTag());
        assertEquals(videoId, videoTags.get(0).getVideoId());

        videoTags = videoTagRepository.findByTag("tag2");

        assertEquals(1, videoTags.size());
        assertEquals("tag2", videoTags.get(0).getTag());
        assertEquals(videoId, videoTags.get(0).getVideoId());
    }

    @Test
    public void testSearchVideos() {
        String tag = "tag1";

        VideoTag videoTag1 = new VideoTag();
        videoTag1.setVideoId(Uuids.timeBased());
        videoTag1.setTag(tag);

        VideoTag videoTag2 = new VideoTag();
        videoTag2.setVideoId(Uuids.timeBased());
        videoTag2.setTag(tag);

        videoTagRepository.save(videoTag1);
        videoTagRepository.save(videoTag2);

        List<VideoTagDTO> videoTagDTOs = videoTagService.searchVideos(tag);

        assertEquals(2, videoTagDTOs.size());
        assertEquals(tag, videoTagDTOs.get(0).getTag());
        assertEquals(tag, videoTagDTOs.get(1).getTag());
    }
}

