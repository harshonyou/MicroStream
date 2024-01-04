package com.example.video.service;

import com.example.video.dto.VideoFeedbackDTO;

import java.util.Optional;
import java.util.UUID;

public interface VideoFeedbackService {
    Optional<VideoFeedbackDTO> submitFeedback(String userId, UUID videoId, boolean likeStatus);
}
