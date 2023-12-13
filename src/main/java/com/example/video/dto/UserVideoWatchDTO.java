package com.example.video.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@ToString
public class UserVideoWatchDTO { // TODO: Add @Serdeable annotation
    private String userId;
    private UUID videoId;
    private Instant watchedTime;
}
