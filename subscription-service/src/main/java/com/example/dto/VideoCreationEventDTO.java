package com.example.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Serdeable
@ToString
public class VideoCreationEventDTO {
    private String userId;
    private UUID videoId;
    private String title;
    private Set<String> tags;
}
