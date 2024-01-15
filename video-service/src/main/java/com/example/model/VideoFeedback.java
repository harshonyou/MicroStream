package com.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Setter
@Getter
@ToString
public class VideoFeedback {
    private String userId;
    private UUID videoId;
    private boolean likeStatus;
}
