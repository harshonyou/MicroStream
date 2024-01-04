package com.example.api;

import io.micronaut.http.client.annotation.Client;

@Client("${video-service.api.url}")
public interface VideoServiceHttpClient {
}
