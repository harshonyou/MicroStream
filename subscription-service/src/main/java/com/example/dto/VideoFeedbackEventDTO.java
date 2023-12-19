package com.example.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@Serdeable
@ToString
public class VideoFeedbackEventDTO {
    private String userId;
    private UUID videoId;
    private boolean likeStatus;
}
