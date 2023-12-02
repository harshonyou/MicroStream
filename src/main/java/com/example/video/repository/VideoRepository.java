package com.example.video.repository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.metadata.schema.ClusteringOrder;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.example.video.dto.VideoDTO;
import com.example.video.service.VideoService;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.datastax.oss.driver.api.core.type.DataTypes.*;


@Singleton
public class VideoRepository implements VideoService {
    public static final String TABLE_VIDEOS = "videos";
    public static final String USER_ID = "user_id";
    public static final String VIDEO_ID = "item_id";
    public static final String VIDEO_TITLE = "title";
    public static final String VIDEO_WATCHED = "watched";

    private PreparedStatement psInsertVideo;
    private PreparedStatement psSelectVideo;
    private PreparedStatement psSelectUserVideo;
    private PreparedStatement psDeleteVideo;
    private PreparedStatement psDeleteUserVideo;

    private CqlSession cqlSession;

    public VideoRepository(CqlSession cqlSession) {
        this.cqlSession = cqlSession;
        prepareStatements();
    }

    public static void createTableVideo(CqlSession cqlSession) {
        cqlSession.execute(SchemaBuilder.createTable(TABLE_VIDEOS)
                .ifNotExists()
                .withPartitionKey(USER_ID, TEXT)
                .withClusteringColumn(VIDEO_ID, TIMEUUID)
                .withColumn(VIDEO_TITLE, TEXT)
                .withColumn(VIDEO_WATCHED, BOOLEAN)
                .withClusteringOrder(VIDEO_ID, ClusteringOrder.DESC)
                .build());
    }

    @Override
    public VideoDTO save(VideoDTO video) {
        if(video.getVideoId() == null) {
            video.setVideoId(Uuids.timeBased());
            cqlSession.execute(psInsertVideo.bind(
                    video.getUserId(),
                    video.getVideoId(),
                    video.getTitle(),
                    video.getWatched()));
            return video;
        }

        VideoDTO toBeUpdated = findById(video.getUserId(), video.getVideoId()).get();
        if(video.getTitle() != null) {
            toBeUpdated.setTitle(video.getTitle());
        }
        if(video.getWatched() != null) {
            toBeUpdated.setWatched(video.getWatched());
        }
        cqlSession.execute(psInsertVideo.bind(
                toBeUpdated.getUserId(),
                toBeUpdated.getVideoId(),
                toBeUpdated.getTitle(),
                toBeUpdated.getWatched()));
        return toBeUpdated;
    }

    @Override
    public Optional<VideoDTO> findById(String userId, UUID videoId) {
        Row row = cqlSession.execute(psSelectVideo.bind(userId, videoId)).one();
        return (row != null) ?
                Optional.ofNullable(mapRowAsVideo(row)) :
                Optional.empty();
    }


    @Override
    public List<VideoDTO> findByUser(String userId) {
        return cqlSession.execute(psSelectUserVideo.bind(userId))
                .all().stream()
                .map(this::mapRowAsVideo)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public void deleteById(String userId, UUID videoId) {
        cqlSession.execute(psDeleteVideo.bind(userId, videoId));
    }

    @Override
    public void deleteByUser(String userId) {
        cqlSession.execute(psDeleteUserVideo.bind(userId));
    }

    @Override
    public void deleteAll() {
        cqlSession.execute(QueryBuilder.truncate(TABLE_VIDEOS).build());
    }

    private VideoDTO mapRowAsVideo(Row row) {
        VideoDTO video = new VideoDTO();
        video.setUserId(row.getString(USER_ID));
        video.setVideoId(row.getUuid(VIDEO_ID));
        video.setTitle(row.getString(VIDEO_TITLE));
        video.setWatched(row.getBoolean(VIDEO_WATCHED));
        return video;
    }

    private void prepareStatements() {
        psInsertVideo = cqlSession.prepare(
                QueryBuilder.insertInto(TABLE_VIDEOS)
                .value(USER_ID, QueryBuilder.bindMarker())
                .value(VIDEO_ID, QueryBuilder.bindMarker())
                .value(VIDEO_TITLE, QueryBuilder.bindMarker())
                .value(VIDEO_WATCHED, QueryBuilder.bindMarker())
                .build());

        psSelectVideo = cqlSession.prepare(
                QueryBuilder.selectFrom(TABLE_VIDEOS)
                .all()
                .whereColumn(USER_ID).isEqualTo(QueryBuilder.bindMarker())
                .whereColumn(VIDEO_ID).isEqualTo(QueryBuilder.bindMarker())
                .build());

        psSelectUserVideo = cqlSession.prepare(
                QueryBuilder.selectFrom(TABLE_VIDEOS)
                .all()
                .whereColumn(USER_ID).isEqualTo(QueryBuilder.bindMarker())
                .build());

        psDeleteVideo = cqlSession.prepare(
                QueryBuilder.deleteFrom(TABLE_VIDEOS)
                .whereColumn(USER_ID).isEqualTo(QueryBuilder.bindMarker())
                .whereColumn(VIDEO_ID).isEqualTo(QueryBuilder.bindMarker())
                .build());

        psDeleteUserVideo = cqlSession.prepare(
                QueryBuilder.deleteFrom(TABLE_VIDEOS)
                .whereColumn(USER_ID).isEqualTo(QueryBuilder.bindMarker())
                .build());
    }
}
