package com.example.video.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@Serdeable
@ToString
public class VideoTagDTO {
    private String tag;
}
