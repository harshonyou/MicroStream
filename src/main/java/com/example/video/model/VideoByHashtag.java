package com.example.video.model;

import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.CqlName;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import static com.example.video.repository.VideoByHashtagRepository.*;

@Getter
@Setter
@Entity
@CqlName(TABLE_HASHTAGS)
public class VideoByHashtag {

    @PartitionKey
    @CqlName(HASHTAG)
    private String hashtag;

    @ClusteringColumn
    @CqlName(VIDEO_ID)
    private UUID videoId;
}
