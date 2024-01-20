package com.example.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Serdeable
@ToString
public class VideoEngagementDTO {
    private String userId;
    private UUID videoId;
    private Instant watchedTime;
}
