package com.example.video.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Serdeable
public class VideoEngagementEventDTO {
    private String userId;
    private UUID videoId;
    private Instant watchedTime;
}
