package com.example.video.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UserVideoWatchDTO {
    private String userId;
    private UUID videoId;
    private Instant watchedTime;
}
