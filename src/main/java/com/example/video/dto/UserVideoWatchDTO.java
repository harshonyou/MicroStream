package com.example.video.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
public class UserVideoWatchDTO {
    private String userId;
    private UUID videoId;
    private LocalTime watchedTime;
}
