package com.example.video.consumer;

public interface MessageListener {
    void receiveNewVideo(String message);
    void receiveVideoWatched(String message);
}
