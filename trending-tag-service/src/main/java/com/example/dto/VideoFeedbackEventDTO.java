package com.example.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Serdeable
public class VideoFeedbackEventDTO {
    private Set<String> tags;
    private boolean likeStatus;
}
