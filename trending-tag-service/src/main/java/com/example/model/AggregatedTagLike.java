package com.example.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@MappedEntity(value = "aggregated_tag_likes")
@Getter
@Setter
@AllArgsConstructor
@Introspected
@Serdeable
public class AggregatedTagLike {

    @Id
    private String tag;

    private Long aggregatedLikes;

    private Timestamp minuteInterval;
}
