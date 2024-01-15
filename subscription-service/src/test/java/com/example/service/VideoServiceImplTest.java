package com.example.service;

import com.example.dto.TagDTO;
import com.example.dto.VideoDTO;
import com.example.mapper.TagMapper;
import com.example.model.User;
import com.example.model.Video;
import com.example.repository.Neo4jTagRepository;
import com.example.repository.Neo4jUserRepository;
import com.example.repository.Neo4jVideoRepository;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Duration;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@MicronautTest(transactional = false, environments = "no-streams")
class VideoServiceImplTest {
    @Inject
    Neo4jVideoRepository videoRepository;

    @Inject
    Neo4jTagRepository tagRepository;

    @Inject
    Neo4jUserRepository userRepository;

    @Inject
    VideoServiceImpl videoService;

    @Mock
    Neo4jVideoRepository mockVideoRepository;

    @AfterEach
    public void tearDown() {
        videoRepository.deleteAll();
        tagRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void testPostVideo() {
        UUID videoId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        String videoName = "video-name";
        Long videoViews = 0L;
        String userId = "user-id";
        String userName = "user-name";
        Set<TagDTO> tags = Set.of(new TagDTO("tag-name"));
        userRepository.addUser(new User(userId, userName));
        tagRepository.addTag(TagMapper.fromDTO(tags.iterator().next()));
        videoService.postVideo(userId, new VideoDTO(videoId, videoName, videoViews), tags);

        Optional<Video> video = videoRepository.findById(videoId);
        assertTrue(video.isPresent());
        assertEquals(videoId, video.get().getId());
        assertEquals(videoName, video.get().getTitle());
        assertEquals(videoViews, video.get().getViews());
    }

    @Test
    public void testWatchVideo() {
        UUID videoId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        String videoName = "video-name";
        Long videoViews = 0L;
        String userId = "user-id";
        String userName = "user-name";
        Set<TagDTO> tags = Set.of(new TagDTO("tag-name"));
        userRepository.addUser(new User(userId, userName));
        tagRepository.addTag(TagMapper.fromDTO(tags.iterator().next()));
        videoService.postVideo(userId, new VideoDTO(videoId, videoName, videoViews), tags);
        videoService.watchVideo(videoId, userId);

        Optional<Video> video = videoRepository.findById(videoId);
        assertTrue(video.isPresent());
        assertEquals(videoId, video.get().getId());
        assertEquals(videoName, video.get().getTitle());
        assertEquals(videoViews + 1, video.get().getViews());
    }

    @Test
    public void testLikeVideo() {
        UUID videoId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        String videoName = "video-name";
        Long videoViews = 0L;
        String userId = "user-id";
        String userName = "user-name";
        Set<TagDTO> tags = Set.of(new TagDTO("tag-name"));
        userRepository.addUser(new User(userId, userName));
        tagRepository.addTag(TagMapper.fromDTO(tags.iterator().next()));
        videoService.postVideo(userId, new VideoDTO(videoId, videoName, videoViews), tags);
        videoService.likeVideo(videoId, userId);

        Optional<Video> video = videoRepository.findById(videoId);
        assertTrue(video.isPresent());
        assertEquals(videoId, video.get().getId());
        assertEquals(videoName, video.get().getTitle());
        assertEquals(videoViews, video.get().getViews());

        boolean isLiked = videoRepository.isLiked(videoId, userId);
        assertTrue(isLiked);
    }

    @Test
    public void testDislikeVideo() {
        UUID videoId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        String videoName = "video-name";
        Long videoViews = 0L;
        String userId = "user-id";
        String userName = "user-name";
        Set<TagDTO> tags = Set.of(new TagDTO("tag-name"));
        userRepository.addUser(new User(userId, userName));
        tagRepository.addTag(TagMapper.fromDTO(tags.iterator().next()));
        videoService.postVideo(userId, new VideoDTO(videoId, videoName, videoViews), tags);
        videoService.likeVideo(videoId, userId);
        videoService.dislikeVideo(videoId, userId);

        Optional<Video> video = videoRepository.findById(videoId);
        assertTrue(video.isPresent());
        assertEquals(videoId, video.get().getId());
        assertEquals(videoName, video.get().getTitle());
        assertEquals(videoViews, video.get().getViews());

        boolean isLiked = videoRepository.isLiked(videoId, userId);
        assertFalse(isLiked);
    }
}