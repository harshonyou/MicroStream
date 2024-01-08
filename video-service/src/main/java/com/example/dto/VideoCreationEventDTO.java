package com.example.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Serdeable
public class VideoCreationEventDTO {
    private String userId;
    private UUID videoId;
    private String title;
    private Set<String> tags;
}
