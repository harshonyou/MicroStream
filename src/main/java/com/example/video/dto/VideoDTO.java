package com.example.video.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class VideoDTO {
    private String userId;
    private UUID videoId;
    private String title;
}
