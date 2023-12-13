package com.example.video.consumer;

public interface MessageListener {
    void handleVideoCreationEvents(String message);
    void handleVideoEngagementEvents(String message);

    void handleVideoFeedbackEvents(String message);
}
