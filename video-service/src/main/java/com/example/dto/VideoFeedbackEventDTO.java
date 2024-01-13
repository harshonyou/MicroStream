package com.example.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Serdeable
public class VideoFeedbackEventDTO {
    private String userId;
    private UUID videoId;
    private Set<String> tags;
    private boolean likeStatus;
}