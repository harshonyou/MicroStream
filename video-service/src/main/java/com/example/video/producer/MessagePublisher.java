package com.example.video.producer;

public interface MessagePublisher {

    void notifyOnNewVideoPosted(String message);

    void notifyOnVideoWatched(String message);

    void notifyOnLikeDislike(String message);
}
