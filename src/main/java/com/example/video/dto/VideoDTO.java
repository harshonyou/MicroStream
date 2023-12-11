package com.example.video.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class VideoDTO {
    private String userId;
    private UUID videoId;
    private String title;
}
