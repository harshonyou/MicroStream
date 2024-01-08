package com.example.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Serdeable
@ToString
public class VideoDTO {
    private String userId;
    private UUID videoId;
    private String title;
    private Set<String> tags;
}
