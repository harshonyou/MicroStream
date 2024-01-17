// Code Generated Via EGL Template

package com.example.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import java.util.UUID;
import java.time.Instant;

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
