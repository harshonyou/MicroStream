package com.example.video.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Setter
@Getter
@ToString
public class LikeVideo {
    private String userId;
    private UUID videoId;
    private boolean likeStatus; // TODO: change to enum
}
