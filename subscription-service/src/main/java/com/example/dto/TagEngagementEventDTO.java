package com.example.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@Serdeable
public class TagEngagementEventDTO {
    private String userId;
    private String tag;
    private boolean subscriptionStatus;
}