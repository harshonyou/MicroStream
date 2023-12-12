package com.example.video.model;

import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import static com.example.video.repository.UserVideoWatchRepository.*;

@Setter
@Getter
@Entity
@CqlName(TABLE_WATCHED_VIDEOS)
public class UserVideoWatch {

    @PartitionKey
    @CqlName(USER_ID)
    private String userId;

    @ClusteringColumn
    @CqlName(VIDEO_ID)
    private UUID videoId;

    @CqlName(WATCHED_TIME)
    private Instant watchedTime;
}
