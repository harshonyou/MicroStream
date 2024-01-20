package com.example.repository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.metadata.schema.ClusteringOrder;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.example.model.VideoTag;
import jakarta.inject.Singleton;

import java.util.List;

import static com.datastax.oss.driver.api.core.type.DataTypes.TEXT;
import static com.datastax.oss.driver.api.core.type.DataTypes.TIMEUUID;

@Singleton
public class CassandraVideoTagRepository implements VideoTagRepository {
    private PreparedStatement psInsertTag;
    private PreparedStatement psSelectTag;

    private final CqlSession cqlSession;

    public CassandraVideoTagRepository(CqlSession cqlSession) {
        this.cqlSession = cqlSession;
//        createTableTag(cqlSession);
        prepareStatements();
    }

    public static void createTableTag(CqlSession cqlSession) {
        cqlSession.execute(SchemaBuilder.createTable(TABLE_TAGS)
                .ifNotExists()
                .withPartitionKey(TAG, TEXT)
                .withClusteringColumn(VIDEO_ID, TIMEUUID)
                .withClusteringOrder(VIDEO_ID, ClusteringOrder.DESC)
                .build());
    }

    @Override
    public VideoTag save(VideoTag videoTag) {
        cqlSession.execute(psInsertTag.bind(
                videoTag.getTag(),
                videoTag.getVideoId()));
        return videoTag;
    }

    @Override
    public List<VideoTag> findByTag(String tag) {
        return cqlSession.execute(psSelectTag.bind(tag))
                .all()
                .stream()
                .map(this::mapRowToVideoTag)
                .toList();
    }

    public void deleteAll() {
        cqlSession.execute(QueryBuilder.truncate(TABLE_TAGS).build());
    }

    private VideoTag mapRowToVideoTag(Row row) {
        VideoTag videoTag = new VideoTag();
        videoTag.setTag(row.getString(TAG));
        videoTag.setVideoId(row.getUuid(VIDEO_ID));
        return videoTag;
    }

    private void prepareStatements() {
        psInsertTag = cqlSession.prepare(
                QueryBuilder.insertInto(TABLE_TAGS)
                        .value(TAG, QueryBuilder.bindMarker())
                        .value(VIDEO_ID, QueryBuilder.bindMarker())
                        .build());

        psSelectTag = cqlSession.prepare(
                QueryBuilder.selectFrom(TABLE_TAGS)
                        .all()
                        .whereColumn(TAG).isEqualTo(QueryBuilder.bindMarker())
                        .build());
    }
}
