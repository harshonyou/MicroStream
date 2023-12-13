package com.example.video.service;

import java.util.UUID;

public interface LikeVideoService {
    void save(String userId, UUID videoId, boolean likeStatus);
}
