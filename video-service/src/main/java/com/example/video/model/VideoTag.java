package com.example.video.model;

import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import static com.example.video.repository.VideoTagRepository.*;

@Getter
@Setter
@Entity
@CqlName(TABLE_TAGS)
public class VideoTag {

    @PartitionKey
    @CqlName(VIDEO_ID)
    private UUID videoId;

    @ClusteringColumn
    @CqlName(TAG)
    private String tag;
}
