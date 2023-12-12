package com.example.video.repository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.metadata.schema.ClusteringOrder;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.example.video.model.UserVideoWatch;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

import static com.datastax.oss.driver.api.core.type.DataTypes.*;
import static com.example.video.repository.UserVideoWatchRepository.*;

@Singleton
public class CassandraUserVideoWatchRepository implements UserVideoWatchRepository {
    private PreparedStatement psInsertUserVideoWatch;
    private PreparedStatement psSelectUserVideoWatch;
    private PreparedStatement psSelectVideoWatch;
    private PreparedStatement psSelectVideoWatchByUser;

    private final CqlSession cqlSession;

    public CassandraUserVideoWatchRepository(CqlSession cqlSession) {
        this.cqlSession = cqlSession;
        prepareStatements();
    }

    public static void createTableUserVideoWatch(CqlSession cqlSession) {
        cqlSession.execute(SchemaBuilder.createTable(TABLE_WATCHED_VIDEOS)
                .ifNotExists()
                .withPartitionKey(USER_ID, TEXT)
                .withClusteringColumn(VIDEO_ID, TIMEUUID)
                .withColumn(WATCHED_TIME, TIMESTAMP)
                .withClusteringOrder(VIDEO_ID, ClusteringOrder.DESC)
                .build());
    }

    @Override
    public UserVideoWatch save(UserVideoWatch userVideoWatch) {
        if(userVideoWatch.getWatchedTime() == null) {
            userVideoWatch.setWatchedTime(java.time.LocalTime.now());
        }

        cqlSession.execute(psInsertUserVideoWatch.bind(
                userVideoWatch.getUserId(),
                userVideoWatch.getVideoId(),
                userVideoWatch.getWatchedTime()));

        return userVideoWatch;
    }

    @Override
    public List<UserVideoWatch> findByUser(String userId) {
        return cqlSession.execute(psSelectVideoWatchByUser.bind(userId))
                .all()
                .stream()
                .map(this::mapRowToUserVideoWatch)
                .toList();
    }

    @Override
    public List<UserVideoWatch> findByVideo(java.util.UUID videoId) {
        return cqlSession.execute(psSelectVideoWatch.bind(videoId))
                .all()
                .stream()
                .map(this::mapRowToUserVideoWatch)
                .toList();
    }

    @Override
    public Optional<UserVideoWatch> findById(String userId, java.util.UUID videoId) {
        Row row = cqlSession.execute(psSelectUserVideoWatch.bind(userId, videoId)).one();
        return (row != null) ?
                Optional.of(mapRowToUserVideoWatch(row)) :
                Optional.empty();
    }

    private UserVideoWatch mapRowToUserVideoWatch(Row row) {
        UserVideoWatch userVideoWatch = new UserVideoWatch();
        userVideoWatch.setUserId(row.getString(USER_ID));
        userVideoWatch.setVideoId(row.getUuid(VIDEO_ID));
        userVideoWatch.setWatchedTime(row.getLocalTime(WATCHED_TIME));
        return userVideoWatch;
    }
    private void prepareStatements() {
        psInsertUserVideoWatch = cqlSession.prepare(
                QueryBuilder.insertInto(TABLE_WATCHED_VIDEOS)
                        .value(USER_ID, QueryBuilder.bindMarker())
                        .value(VIDEO_ID, QueryBuilder.bindMarker())
                        .value(WATCHED_TIME, QueryBuilder.bindMarker())
                        .build());

        psSelectUserVideoWatch = cqlSession.prepare(
                QueryBuilder.selectFrom(TABLE_WATCHED_VIDEOS)
                        .all()
                        .whereColumn(USER_ID).isEqualTo(QueryBuilder.bindMarker())
                        .whereColumn(VIDEO_ID).isEqualTo(QueryBuilder.bindMarker())
                        .build());

//        psSelectVideoWatch = cqlSession.prepare(
//                QueryBuilder.selectFrom(TABLE_WATCHED_VIDEOS)
//                        .all()
//                        .whereColumn(VIDEO_ID).isEqualTo(QueryBuilder.bindMarker())
//                        .build());

        psSelectVideoWatchByUser = cqlSession.prepare(
                QueryBuilder.selectFrom(TABLE_WATCHED_VIDEOS)
                        .all()
                        .whereColumn(USER_ID).isEqualTo(QueryBuilder.bindMarker())
                        .build());
    }
}
