package com.example.video.model;

import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

import static com.example.video.repository.VideoEngagementRepository.*;

@Getter
@Setter
@Entity
@CqlName(TABLE_VIDEO_ENGAGEMENTS)
public class UserEngagement {

    @PartitionKey
    @CqlName(USER_ID)
    private String userId;

    @ClusteringColumn
    @CqlName(VIDEO_ID)
    private UUID videoId;

    @CqlName(WATCHED_TIME)
    private Instant watchedTime;
}
