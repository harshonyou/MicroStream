package com.example.serde;

import com.example.dto.VideoFeedbackEventDTO;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(startApplication = false, environments = "no-streams")
class VideoFeedbackEventDTOSerdeTest {
    @Inject
    VideoFeedbackEventDTOSerde videoFeedbackEventDTOSerde;

    @Test
    public void testSerializeAndDeserialize() {
        VideoFeedbackEventDTO originalDto = new VideoFeedbackEventDTO();
        originalDto.setTags(Set.of("exampleTag1", "exampleTag2"));
        originalDto.setLikeStatus(true);

        byte[] serializedData = videoFeedbackEventDTOSerde.serialize("topic", originalDto);

        VideoFeedbackEventDTO deserializedDto = videoFeedbackEventDTOSerde.deserialize("topic", serializedData);

        assertEquals(originalDto.getTags(), deserializedDto.getTags());
        assertEquals(originalDto.isLikeStatus(), deserializedDto.isLikeStatus());
    }
}