package com.example.video.model;

import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import static com.example.video.repository.VideoRepository.*;

@Setter
@Getter
@Entity
@CqlName(TABLE_VIDEOS)
public class Video {

    @PartitionKey
    @CqlName(USER_ID)
    private String userId;

    @ClusteringColumn
    @CqlName(VIDEO_ID)
    private UUID videoId;

    @CqlName(VIDEO_TITLE)
    private String title;
}
