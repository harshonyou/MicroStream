package com.example.repository;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.example.model.VideoTag;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false, environments = "no-streams")
class CassandraVideoTagRepositoryTest {
    @Inject
    CassandraVideoTagRepository videoTagRepository;

    @AfterEach
    public void tearDown() {
        videoTagRepository.deleteAll();
    }

    @Test
    public void testSaveVideoTag() {
        VideoTag videoTag = new VideoTag();
        videoTag.setTag("TestTag");
        videoTag.setVideoId(Uuids.timeBased());

        VideoTag savedVideoTag = videoTagRepository.save(videoTag);

        assertEquals("TestTag", savedVideoTag.getTag());
        assertNotNull(savedVideoTag.getVideoId());
    }

    @Test
    public void testFindByTag() {
        VideoTag videoTag1 = new VideoTag();
        videoTag1.setTag("Tag1");
        videoTag1.setVideoId(Uuids.timeBased());

        VideoTag videoTag2 = new VideoTag();
        videoTag2.setTag("Tag2");
        videoTag2.setVideoId(Uuids.timeBased());

        videoTagRepository.save(videoTag1);
        videoTagRepository.save(videoTag2);

        List<VideoTag> foundVideoTags = videoTagRepository.findByTag("Tag1");

        assertEquals(1, foundVideoTags.size());
        assertEquals("Tag1", foundVideoTags.get(0).getTag());
    }

    @Test
    public void testDeleteAllVideoTags() {
        VideoTag videoTag1 = new VideoTag();
        videoTag1.setTag("Tag1");
        videoTag1.setVideoId(Uuids.timeBased());

        VideoTag videoTag2 = new VideoTag();
        videoTag2.setTag("Tag2");
        videoTag2.setVideoId(Uuids.timeBased());

        videoTagRepository.save(videoTag1);
        videoTagRepository.save(videoTag2);

        videoTagRepository.deleteAll();

        List<VideoTag> allVideoTags = videoTagRepository.findByTag("Tag1");

        assertEquals(0, allVideoTags.size());
    }
}

