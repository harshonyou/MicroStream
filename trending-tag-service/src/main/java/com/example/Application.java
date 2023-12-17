package com.example;

import com.example.dto.TagsLikeEventDTO;
import com.example.helper.producer.TagsLikeEventClient;
import io.micronaut.runtime.Micronaut;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Inject;

import java.util.HashSet;
import java.util.Set;

public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}