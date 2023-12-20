package com.example.video.service;

import java.util.UUID;

public interface VideoFeedbackService {
    void save(String userId, UUID videoId, boolean likeStatus);
}
