package com.example.video.producer;

public interface MessagePublisher {

    void sendNewVideo(String message);

    void sendVideoWatched(String message);
}
