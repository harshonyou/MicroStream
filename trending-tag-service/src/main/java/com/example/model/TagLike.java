package com.example.model;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import java.sql.Timestamp;

@Data
@MappedEntity(value = "aggregated_tag_likes")
@Introspected
@Serdeable
public class TagLike {

    @Id
    private String tag;

    private Integer totalLikes;

    private Timestamp minuteInterval;
}
