package com.example.video.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class VideoTagDTO {
    private String tag;
    private UUID videoId;
}
