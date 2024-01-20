package com.example.repository;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.example.model.Video;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false, environments = "no-streams")
class CassandraVideoRepositoryTest {
    @Inject
    CassandraVideoRepository videoRepository;

    @AfterEach
    public void tearDown() {
        videoRepository.deleteAll();
    }

    @Test
    public void testSaveVideo() {
        Video video = new Video();
        video.setUserId("testUser");
        video.setTitle("Test Video");
        Set<String> tags = new HashSet<>();
        tags.add("Tag1");
        tags.add("Tag2");
        video.setTags(tags);

        Video savedVideo = videoRepository.save(video);

        assertNotNull(savedVideo.getVideoId());
        assertEquals("testUser", savedVideo.getUserId());
        assertEquals("Test Video", savedVideo.getTitle());
        assertEquals(tags, savedVideo.getTags());

        Video updatedVideo = videoRepository.save(savedVideo);

        assertEquals(savedVideo.getVideoId(), updatedVideo.getVideoId());
        assertEquals("testUser", updatedVideo.getUserId());
        assertEquals("Test Video", updatedVideo.getTitle());
        assertEquals(tags, updatedVideo.getTags());

        video.setVideoId(Uuids.timeBased());
        Video savedVideo2 = videoRepository.save(video);

        assertNotNull(savedVideo2.getVideoId());
        assertEquals("testUser", savedVideo2.getUserId());
        assertEquals("Test Video", savedVideo2.getTitle());
        assertEquals(tags, savedVideo2.getTags());
    }

    @Test
    public void testFindVideoById() {
        Video video = new Video();
        video.setUserId("testUser");
        video.setTitle("Test Video");
        Set<String> tags = new HashSet<>();
        tags.add("Tag1");
        tags.add("Tag2");
        video.setTags(tags);

        Video savedVideo = videoRepository.save(video);

        Optional<Video> foundVideo = videoRepository.findById("testUser", savedVideo.getVideoId());

        assertTrue(foundVideo.isPresent());
        assertEquals(savedVideo.getVideoId(), foundVideo.get().getVideoId());
    }

    @Test
    public void testFindByUser() {
        Video video1 = new Video();
        video1.setUserId("testUser1");
        video1.setTitle("Test Video 1");
        Set<String> tags1 = new HashSet<>();
        tags1.add("Tag1");
        tags1.add("Tag2");
        video1.setTags(tags1);

        Video video2 = new Video();
        video2.setUserId("testUser2");
        video2.setTitle("Test Video 2");
        Set<String> tags2 = new HashSet<>();
        tags2.add("Tag3");
        tags2.add("Tag4");
        video2.setTags(tags2);

        videoRepository.save(video1);
        videoRepository.save(video2);

        List<Video> user1Videos = videoRepository.findByUser("testUser1");
        List<Video> user2Videos = videoRepository.findByUser("testUser2");

        assertEquals(1, user1Videos.size());
        assertEquals(1, user2Videos.size());
    }

    @Test
    public void testFindVideoByVideoId() {
        Video video = new Video();
        video.setUserId("testUser");
        video.setTitle("Test Video");
        Set<String> tags = new HashSet<>();
        tags.add("Tag1");
        tags.add("Tag2");
        video.setTags(tags);

        Video savedVideo = videoRepository.save(video);

        Optional<Video> foundVideo = videoRepository.findByVideoId(savedVideo.getVideoId());

        assertTrue(foundVideo.isPresent());
        assertEquals(savedVideo.getVideoId(), foundVideo.get().getVideoId());
    }

    @Test
    public void testDeleteVideoById() {
        Video video = new Video();
        video.setUserId("testUser");
        video.setTitle("Test Video");
        Set<String> tags = new HashSet<>();
        tags.add("Tag1");
        tags.add("Tag2");
        video.setTags(tags);

        Video savedVideo = videoRepository.save(video);

        videoRepository.deleteById("testUser", savedVideo.getVideoId());

        Optional<Video> foundVideo = videoRepository.findById("testUser", savedVideo.getVideoId());

        assertFalse(foundVideo.isPresent());
    }

    @Test
    public void testDeleteVideosByUser() {
        Video video1 = new Video();
        video1.setUserId("testUser");
        video1.setTitle("Test Video 1");
        Set<String> tags1 = new HashSet<>();
        tags1.add("Tag1");
        tags1.add("Tag2");
        video1.setTags(tags1);

        Video video2 = new Video();
        video2.setUserId("testUser");
        video2.setTitle("Test Video 2");
        Set<String> tags2 = new HashSet<>();
        tags2.add("Tag3");
        tags2.add("Tag4");
        video2.setTags(tags2);

        videoRepository.save(video1);
        videoRepository.save(video2);

        videoRepository.deleteByUser("testUser");

        List<Video> userVideos = videoRepository.findByUser("testUser");

        assertEquals(0, userVideos.size());
    }

    @Test
    public void testDeleteAllVideos() {
        Video video1 = new Video();
        video1.setUserId("testUser1");
        video1.setTitle("Test Video 1");
        Set<String> tags1 = new HashSet<>();
        tags1.add("Tag1");
        tags1.add("Tag2");
        video1.setTags(tags1);

        Video video2 = new Video();
        video2.setUserId("testUser2");
        video2.setTitle("Test Video 2");
        Set<String> tags2 = new HashSet<>();
        tags2.add("Tag3");
        tags2.add("Tag4");
        video2.setTags(tags2);

        videoRepository.save(video1);
        videoRepository.save(video2);

        videoRepository.deleteAll();

        List<Video> user1Videos = videoRepository.findByUser("testUser1");
        List<Video> user2Videos = videoRepository.findByUser("testUser2");

        assertEquals(0, user1Videos.size());
        assertEquals(0, user2Videos.size());
    }
}