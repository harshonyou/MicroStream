package com.example.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@MappedEntity(value = "aggregated_tag_likes")
@AllArgsConstructor
@Introspected
@Serdeable
public class AggregatedTagLike {

    @Id
    private String tag;

    private Long aggregatedLikes;

    private Timestamp minuteInterval;
}
