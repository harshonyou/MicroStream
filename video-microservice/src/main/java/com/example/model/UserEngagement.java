package com.example.model;

import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import com.example.repository.VideoEngagementRepository;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@CqlName(VideoEngagementRepository.TABLE_VIDEO_ENGAGEMENTS)
public class UserEngagement {

    @PartitionKey
    @CqlName(VideoEngagementRepository.USER_ID)
    private String userId;

    @ClusteringColumn
    @CqlName(VideoEngagementRepository.VIDEO_ID)
    private UUID videoId;

    @CqlName(VideoEngagementRepository.WATCHED_TIME)
    private Instant watchedTime;
}