package com.example.video.repository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.metadata.schema.ClusteringOrder;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.example.video.model.VideoTag;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.datastax.oss.driver.api.core.type.DataTypes.TEXT;
import static com.datastax.oss.driver.api.core.type.DataTypes.TIMEUUID;

@Singleton
public class CassandraVideoTagRepository implements VideoTagRepository {
    private PreparedStatement psInsertTag;
    private PreparedStatement psSelectTagsByVideoId;

    private final CqlSession cqlSession;

    public CassandraVideoTagRepository(CqlSession cqlSession) {
        this.cqlSession = cqlSession;
        createTableTag();
        prepareStatements();
    }

    public void createTableTag() {
        cqlSession.execute(SchemaBuilder.createTable(TABLE_TAGS)
                .ifNotExists()
                .withPartitionKey(VIDEO_ID, TIMEUUID)
                .withClusteringColumn(TAG, TEXT)
                .withClusteringOrder(TAG, ClusteringOrder.ASC)
                .build());
    }

    @Override
    public VideoTag save(VideoTag videoTag) {
        cqlSession.execute(psInsertTag.bind(
                videoTag.getVideoId(),
                videoTag.getTag()));
        return videoTag;
    }

    public List<VideoTag> findTagsByVideoId(UUID videoId) {
        return cqlSession.execute(psSelectTagsByVideoId.bind(videoId))
                .all()
                .stream()
                .map(this::mapRowToVideoTag)
                .collect(Collectors.toList());
    }

    private VideoTag mapRowToVideoTag(Row row) {
        VideoTag videoTag = new VideoTag();
        videoTag.setTag(row.getString(TAG));
        return videoTag;
    }

    private void prepareStatements() {
        psInsertTag = cqlSession.prepare(
                QueryBuilder.insertInto(TABLE_TAGS)
                        .value(VIDEO_ID, QueryBuilder.bindMarker())
                        .value(TAG, QueryBuilder.bindMarker())
                        .build());

        psSelectTagsByVideoId = cqlSession.prepare(
                QueryBuilder.selectFrom(TABLE_TAGS)
                        .column(TAG)
                        .whereColumn(VIDEO_ID).isEqualTo(QueryBuilder.bindMarker())
                        .build());
    }
}
