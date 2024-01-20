package com.example.stream;

import com.example.repository.AggregatedTagLikeRepository;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.apache.kafka.streams.kstream.Windowed;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.PunctuationType;
import org.apache.kafka.streams.processor.Punctuator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.Duration;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(environments = "no-streams")
class TopNTagsTransformerTest {

    @Inject
    AggregatedTagLikeRepository aggregatedTagLikeRepository;

    @Mock
    private ProcessorContext mockContext;

    @Test
    public void constructorShouldThrowExceptionForInvalidN() {
        assertThrows(IllegalArgumentException.class, () -> new TopNTagsTransformer(0, null, aggregatedTagLikeRepository));
        assertThrows(IllegalArgumentException.class, () -> new TopNTagsTransformer(-1, null, aggregatedTagLikeRepository));
    }

    @Test
    public void testInit() {
        MockitoAnnotations.openMocks(this);
        TopNTagsTransformer topNTagsTransformer = new TopNTagsTransformer(10, Duration.ofMinutes(1), aggregatedTagLikeRepository);
        topNTagsTransformer.init(mockContext);
        verify(mockContext).schedule(eq(Duration.ofMinutes(1)), eq(PunctuationType.WALL_CLOCK_TIME), any(Punctuator.class));
    }
}