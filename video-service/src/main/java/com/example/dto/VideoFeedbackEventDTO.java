// Code Generated Via EGL Template

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
public class VideoFeedbackEventDTO {
    private String userId;
    private UUID videoId;
    private Set<String> tags;
    private boolean likeStatus;
}
