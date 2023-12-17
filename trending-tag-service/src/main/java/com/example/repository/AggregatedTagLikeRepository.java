package com.example.repository;


import com.example.model.AggregatedTagLike;
import com.example.dto.AggregatedTagLikeDTO;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

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
            "LIMIT 10", nativeQuery = true)
    List<AggregatedTagLikeDTO> findTopTagsByHourlyLikes();
}