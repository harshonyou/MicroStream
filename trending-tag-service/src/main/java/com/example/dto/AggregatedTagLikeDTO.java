package com.example.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@Serdeable
public class AggregatedTagLikeDTO {
    private final String tag;
    private final Long hourlyLikes;
}
