package com.example.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import picocli.CommandLine;

@Getter
@AllArgsConstructor
@ToString
@Serdeable
public class PastIntervalAggregatedTagLikeDTO {
    private final String tag;
    private final Long intervalLikes;

    public static String formatPastIntervalAggregatedTagLikeDTO(PastIntervalAggregatedTagLikeDTO pastIntervalAggregatedTagLikeDTO) {
        return CommandLine.Help.Ansi.AUTO.string(String.format(
                "@|bold,underline %s|@: @|italic %s|@",
                pastIntervalAggregatedTagLikeDTO.getTag(),
                pastIntervalAggregatedTagLikeDTO.getIntervalLikes()
        ));
    }
}