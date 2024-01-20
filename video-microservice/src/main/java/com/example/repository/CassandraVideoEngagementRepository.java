package com.example.repository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.metadata.schema.ClusteringOrder;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.example.model.UserEngagement;
import jakarta.inject.Singleton;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static com.datastax.oss.driver.api.core.type.DataTypes.*;

@Singleton
public class CassandraVideoEngagementRepository implements VideoEngagementRepository {
    private PreparedStatement psInsertUserVideoWatch;
    private PreparedStatement psSelectUserVideoWatch;
    private PreparedStatement psSelectVideoWatchByUser;

    private final CqlSession cqlSession;

    public CassandraVideoEngagementRepository(CqlSession cqlSession) {
        this.cqlSession = cqlSession;
//        createTableUserVideoWatch(cqlSession);
        prepareStatements();
    }

    public static void createTableUserVideoWatch(CqlSession cqlSession) {
        cqlSession.execute(SchemaBuilder.createTable(TABLE_VIDEO_ENGAGEMENTS)
                .ifNotExists()
                .withPartitionKey(USER_ID, TEXT)
                .withClusteringColumn(VIDEO_ID, TIMEUUID)
                .withColumn(WATCHED_TIME, TIMESTAMP)
                .withClusteringOrder(VIDEO_ID, ClusteringOrder.DESC)
                .build());
    }

    @Override
    public UserEngagement save(UserEngagement userEngagement) {
        if(userEngagement.getWatchedTime() == null) {
            userEngagement.setWatchedTime(Instant.now());
        }

        cqlSession.execute(psInsertUserVideoWatch.bind(
                userEngagement.getUserId(),
                userEngagement.getVideoId(),
                userEngagement.getWatchedTime()));

        return userEngagement;
    }

    @Override
    public List<UserEngagement> findByUser(String userId) {
        return cqlSession.execute(psSelectVideoWatchByUser.bind(userId))
                .all()
                .stream()
                .map(this::mapRowToUserVideoWatch)
                .toList();
    }

    @Override
    public Optional<UserEngagement> findById(String userId, java.util.UUID videoId) {
        Row row = cqlSession.execute(psSelectUserVideoWatch.bind(userId, videoId)).one();
        return (row != null) ?
                Optional.of(mapRowToUserVideoWatch(row)) :
                Optional.empty();
    }

    public void deleteAll() {
        cqlSession.execute(QueryBuilder.truncate(TABLE_VIDEO_ENGAGEMENTS).build());
    }

    private UserEngagement mapRowToUserVideoWatch(Row row) {
        UserEngagement userVideoWatch = new UserEngagement();
        userVideoWatch.setUserId(row.getString(USER_ID));
        userVideoWatch.setVideoId(row.getUuid(VIDEO_ID));
        userVideoWatch.setWatchedTime(row.getInstant(WATCHED_TIME));
        return userVideoWatch;
    }

    private void prepareStatements() {
        psInsertUserVideoWatch = cqlSession.prepare(
                QueryBuilder.insertInto(TABLE_VIDEO_ENGAGEMENTS)
                        .value(USER_ID, QueryBuilder.bindMarker())
                        .value(VIDEO_ID, QueryBuilder.bindMarker())
                        .value(WATCHED_TIME, QueryBuilder.bindMarker())
                        .build());

        psSelectUserVideoWatch = cqlSession.prepare(
                QueryBuilder.selectFrom(TABLE_VIDEO_ENGAGEMENTS)
                        .all()
                        .whereColumn(USER_ID).isEqualTo(QueryBuilder.bindMarker())
                        .whereColumn(VIDEO_ID).isEqualTo(QueryBuilder.bindMarker())
                        .build());

        psSelectVideoWatchByUser = cqlSession.prepare(
                QueryBuilder.selectFrom(TABLE_VIDEO_ENGAGEMENTS)
                        .all()
                        .whereColumn(USER_ID).isEqualTo(QueryBuilder.bindMarker())
                        .build());
    }
}
