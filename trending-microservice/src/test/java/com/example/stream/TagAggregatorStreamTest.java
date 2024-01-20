package com.example.stream;

import com.example.dto.VideoFeedbackEventDTO;
import com.example.helper.producer.TagsLikeEventClient;
import com.example.model.AggregatedTagLike;
import com.example.repository.AggregatedTagLikeRepository;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.apache.kafka.streams.KafkaStreams;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest(environments = "streams")
class TagAggregatorStreamTest {
    @Inject
    private AggregatedTagLikeRepository aggregatedTagLikeRepository;

    @Inject
    private TagsLikeEventClient tagsLikeEventClient;

    @Inject
    KafkaStreams kStreams;

    @BeforeEach
    public void setUp() {
        aggregatedTagLikeRepository.deleteAll();
        assertEquals(0, aggregatedTagLikeRepository.count());
    }

    @AfterEach
    public void cleanUp() {
        kStreams.close();
    }

    @Test
    public void testTagAggregatorStream() {
        tagsLikeEventClient.send("key1", new VideoFeedbackEventDTO(Set.of("tag1", "tag2"), true));
        tagsLikeEventClient.send("key2", new VideoFeedbackEventDTO(Set.of("tag1", "tag3"), true));
        tagsLikeEventClient.send("key3", new VideoFeedbackEventDTO(Set.of("tag1", "tag2", "tag3"), false));
        tagsLikeEventClient.send("key4", new VideoFeedbackEventDTO(Set.of("tag1", "tag2"), true));
        tagsLikeEventClient.send("key4", new VideoFeedbackEventDTO(Set.of("tag1", "tag4"), true));
        tagsLikeEventClient.send("key4", new VideoFeedbackEventDTO(Set.of("tag4"), true));


        Awaitility.await().atMost(2, TimeUnit.MINUTES)
                .until(() -> aggregatedTagLikeRepository.count() == 3);

        AggregatedTagLike tag1 = aggregatedTagLikeRepository.findById("tag1").get();
        assertEquals(4L, tag1.getAggregatedLikes());

        AggregatedTagLike tag2 = aggregatedTagLikeRepository.findById("tag2").get();
        assertEquals(2L, tag2.getAggregatedLikes());

        AggregatedTagLike tag3 = aggregatedTagLikeRepository.findById("tag4").get();
        assertEquals(2L, tag3.getAggregatedLikes());
    }
}