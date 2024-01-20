package com.example.service;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.example.dto.VideoDTO;
import com.example.model.Video;
import com.example.repository.CassandraVideoRepository;
import com.example.repository.CassandraVideoTagRepository;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false, environments = "no-streams")
class VideoServiceImplTest {
    @Inject
    CassandraVideoRepository videoRepository;

    @Inject
    CassandraVideoTagRepository videoTagRepository;

    @Inject
    VideoTagServiceImpl videoTagService;

    @Inject
    VideoServiceImpl videoService;

    @AfterEach
    public void tearDown() {
        videoRepository.deleteAll();
        videoTagRepository.deleteAll();
    }

    @Test
    public void testPost() {
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setUserId("user1");
        videoDTO.setVideoId(Uuids.timeBased());
        videoDTO.setTitle("Test Video");
        Set<String> tags = new HashSet<>();
        tags.add("tag1");
        tags.add("tag2");
        videoDTO.setTags(tags);

        VideoDTO result = videoService.post(videoDTO);

        assertEquals(videoDTO.getUserId(), result.getUserId());
        assertEquals(videoDTO.getVideoId(), result.getVideoId());
        assertEquals(videoDTO.getTitle(), result.getTitle());
        assertEquals(videoDTO.getTags(), result.getTags());
    }

    @Test
    public void testPostWithNoTag() {
        VideoDTO videoDTO = new VideoDTO();
        videoDTO.setUserId("user1");
        videoDTO.setVideoId(Uuids.timeBased());
        videoDTO.setTitle("Test Video");

        VideoDTO result = videoService.post(videoDTO);

        assertEquals(videoDTO.getUserId(), result.getUserId());
        assertEquals(videoDTO.getVideoId(), result.getVideoId());
        assertEquals(videoDTO.getTitle(), result.getTitle());
        assertEquals(videoDTO.getTags(), result.getTags());
    }

    @Test
    public void testSearch() {
        UUID videoId = Uuids.timeBased();
        Video video = new Video();
        video.setVideoId(videoId);
        video.setUserId("user1");
        video.setTitle("Test Video");
        Set<String> tags = new HashSet<>();
        tags.add("tag1");
        tags.add("tag2");
        video.setTags(tags);

        videoRepository.save(video);

        Optional<VideoDTO> result = videoService.search(videoId);

        assertTrue(result.isPresent());
        assertEquals(videoId, result.get().getVideoId());
        assertEquals("user1", result.get().getUserId());
        assertEquals("Test Video", result.get().getTitle());
        assertEquals(tags, result.get().getTags());
    }

    @Test
    public void testSearchWithNonExistingVideo() {
        UUID videoId = Uuids.timeBased();

        Optional<VideoDTO> result = videoService.search(videoId);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testFetch() {
        UUID videoId = Uuids.timeBased();
        Video video = new Video();
        video.setVideoId(videoId);
        video.setUserId("user1");
        video.setTitle("Test Video");
        Set<String> tags = new HashSet<>();
        tags.add("tag1");
        tags.add("tag2");
        video.setTags(tags);

        videoRepository.save(video);

        Optional<VideoDTO> result = videoService.fetch("user1", videoId);

        assertTrue(result.isPresent());
        assertEquals(videoId, result.get().getVideoId());
        assertEquals("user1", result.get().getUserId());
        assertEquals("Test Video", result.get().getTitle());
        assertEquals(tags, result.get().getTags());
    }

    @Test
    public void testFetchWithNonExistingVideo() {
        UUID videoId = Uuids.timeBased();

        Optional<VideoDTO> result = videoService.fetch("user1", videoId);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetUserPosts() {
        String userId = "user1";
        Video video1 = new Video();
        video1.setVideoId(Uuids.timeBased());
        video1.setUserId(userId);
        video1.setTitle("Video 1");
        Set<String> tags1 = new HashSet<>();
        tags1.add("tag1");
        video1.setTags(tags1);

        Video video2 = new Video();
        video2.setVideoId(Uuids.timeBased());
        video2.setUserId(userId);
        video2.setTitle("Video 2");
        Set<String> tags2 = new HashSet<>();
        tags2.add("tag2");
        video2.setTags(tags2);

        videoRepository.save(video1);
        videoRepository.save(video2);

        List<VideoDTO> result = videoService.getUserPosts(userId);

        assertEquals(2, result.size());
        assertEquals(video1.getVideoId(), result.get(1).getVideoId());
        assertEquals(video2.getVideoId(), result.get(0).getVideoId());
    }

    @Test
    public void testRemove() {
        String userId = "user1";
        UUID videoId = Uuids.timeBased();

        Video video = new Video();
        video.setVideoId(videoId);
        video.setUserId(userId);
        video.setTitle("Test Video");
        Set<String> tags = new HashSet<>();
        tags.add("tag1");
        tags.add("tag2");
        video.setTags(tags);

        videoRepository.save(video);

        videoService.remove(userId, videoId);

        Optional<Video> result = videoRepository.findById(userId, videoId);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testRemoveUserPosts() {
        String userId = "user1";
        UUID videoId = Uuids.timeBased();

        Video video = new Video();
        video.setVideoId(videoId);
        video.setUserId(userId);
        video.setTitle("Test Video");
        Set<String> tags = new HashSet<>();
        tags.add("tag1");
        tags.add("tag2");
        video.setTags(tags);

        videoRepository.save(video);

        videoService.removeUserPosts(userId);

        Optional<Video> result = videoRepository.findById(userId, videoId);

        assertTrue(result.isEmpty());
    }


    @Test
    public void testRemoveAllPosts() {
        String userId = "user1";
        UUID videoId = Uuids.timeBased();

        Video video = new Video();
        video.setVideoId(videoId);
        video.setUserId(userId);
        video.setTitle("Test Video");
        Set<String> tags = new HashSet<>();
        tags.add("tag1");
        tags.add("tag2");
        video.setTags(tags);

        videoRepository.save(video);

        videoService.removeAllPosts();

        Optional<Video> result = videoRepository.findById(userId, videoId);

        assertTrue(result.isEmpty());
    }
}

