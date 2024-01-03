package com.example.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Introspected
@Serdeable
public class RecommendedVideoDTO {
    private UUID id;
    private String title;
    // Subset of tags that the recommended video is affiliated with
    private Set<String> affiliatedTags;
    private Long views;
}
