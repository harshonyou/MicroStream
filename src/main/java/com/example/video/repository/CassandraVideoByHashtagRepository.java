package com.example.video.repository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.metadata.schema.ClusteringOrder;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.example.video.model.VideoByHashtag;
import jakarta.inject.Singleton;

import java.util.List;

import static com.datastax.oss.driver.api.core.type.DataTypes.TEXT;
import static com.datastax.oss.driver.api.core.type.DataTypes.TIMEUUID;

@Singleton
public class CassandraVideoByHashtagRepository implements VideoByHashtagRepository {
    private PreparedStatement psInsertHashtag;
    private PreparedStatement psSelectHashtag;

    private final CqlSession cqlSession;

    public CassandraVideoByHashtagRepository(CqlSession cqlSession) {
        this.cqlSession = cqlSession;
        prepareStatements();
    }

    public static void createTableHashtag(CqlSession cqlSession) {
        cqlSession.execute(SchemaBuilder.createTable(TABLE_HASHTAGS)
                .ifNotExists()
                .withPartitionKey(HASHTAG, TEXT)
                .withClusteringColumn(VIDEO_ID, TIMEUUID)
                .withClusteringOrder(VIDEO_ID, ClusteringOrder.DESC)
                .build());
    }

    @Override
    public VideoByHashtag save(VideoByHashtag videoByHashtag) {
        cqlSession.execute(psInsertHashtag.bind(
                videoByHashtag.getHashtag(),
                videoByHashtag.getVideoId()));
        return videoByHashtag;
    }

    @Override
    public List<VideoByHashtag> findByHashtag(String hashtag) {
        return cqlSession.execute(psSelectHashtag.bind(hashtag))
                .all()
                .stream()
                .map(this::mapRowToVideoByHashtag)
                .toList();
    }

    private VideoByHashtag mapRowToVideoByHashtag(Row row) {
        VideoByHashtag videoByHashtag = new VideoByHashtag();
        videoByHashtag.setHashtag(row.getString(HASHTAG));
        videoByHashtag.setVideoId(row.getUuid(VIDEO_ID));
        return videoByHashtag;
    }

    private void prepareStatements() {
        psInsertHashtag = cqlSession.prepare(
                QueryBuilder.insertInto(TABLE_HASHTAGS)
                        .value(HASHTAG, QueryBuilder.bindMarker())
                        .value(VIDEO_ID, QueryBuilder.bindMarker())
                        .build());

        psSelectHashtag = cqlSession.prepare(
                QueryBuilder.selectFrom(TABLE_HASHTAGS)
                        .all()
                        .whereColumn(HASHTAG).isEqualTo(QueryBuilder.bindMarker())
                        .build());
    }
}
