package com.example.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Serdeable
@ToString
public class VideoEngagementEventDTO {
    private String userId;
    private UUID videoId;
    private Instant watchedTime;
}
