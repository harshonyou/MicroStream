package com.example.service;

import com.example.dto.VideoFeedbackDTO;

import java.util.Optional;
import java.util.UUID;

public interface VideoFeedbackService {
    Optional<VideoFeedbackDTO> submitFeedback(String userId, UUID videoId, boolean likeStatus);
}
