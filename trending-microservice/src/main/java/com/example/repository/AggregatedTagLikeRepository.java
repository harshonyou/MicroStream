package com.example.repository;


import com.example.dto.PastIntervalAggregatedTagLikeDTO;
import com.example.model.AggregatedTagLike;
import com.example.dto.CurrentHourAggregatedTagLikeDTO;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

import static com.example.util.IntervalConverter.convertToPostgresInterval;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface AggregatedTagLikeRepository extends CrudRepository<AggregatedTagLike, String> {
    @Query(value = "WITH current_hour_aggregated AS ( " +
                    "    SELECT tag, " +
                    "           SUM(aggregated_likes) AS hourly_likes, " +
                    "           DATE_TRUNC('hour', minute_interval) AS hour_interval " +
                    "    FROM aggregated_tag_likes " +
                    "    WHERE minute_interval >= DATE_TRUNC('hour', NOW()) " +
                    "      AND minute_interval < DATE_TRUNC('hour', NOW()) + INTERVAL '1 hour' " +
                    "    GROUP BY tag, hour_interval " +
                    ") " +
                    "SELECT tag, hourly_likes " +
                    "FROM current_hour_aggregated " +
                    "ORDER BY hourly_likes DESC " +
                    "LIMIT :limit",
            nativeQuery = true)
    List<CurrentHourAggregatedTagLikeDTO> findTopTagsOfCurrentHourWindow(Integer limit);

    @Query(value = "WITH current_interval_aggregated AS ( " +
                    "    SELECT tag, " +
                    "           SUM(aggregated_likes) AS interval_likes " +
                    "    FROM aggregated_tag_likes " +
                    "    WHERE minute_interval >= NOW() - CAST(:interval AS interval) " +
                    "      AND minute_interval < NOW() " +
                    "    GROUP BY tag " +
                    ")  " +
                    "SELECT tag, interval_likes " +
                    "FROM current_interval_aggregated " +
                    "ORDER BY interval_likes DESC " +
                    "LIMIT :limit",
            nativeQuery = true)
    List<PastIntervalAggregatedTagLikeDTO> findTopTagsByIntervalLikes(String interval, Integer limit);

    default List<PastIntervalAggregatedTagLikeDTO> findTopTagsByCustomInterval(String interval, Integer limit) {
        return findTopTagsByIntervalLikes(convertToPostgresInterval(interval), limit);
    }
}
