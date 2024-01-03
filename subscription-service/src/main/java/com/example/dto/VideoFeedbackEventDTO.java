package com.example.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Serdeable
@ToString
public class VideoFeedbackEventDTO {
    private String userId;
    private UUID videoId;
    private boolean likeStatus;
}
