package com.example.model;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Serdeable
public class TagLikeDTO {
    private final String tag;
    private final int hourlyLikes;
}
