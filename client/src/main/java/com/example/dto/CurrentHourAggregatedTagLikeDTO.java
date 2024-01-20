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
public class CurrentHourAggregatedTagLikeDTO {
    private final String tag;
    private final Long hourlyLikes;

    public static String formatCurrentHourAggregatedTagLikeDTO(CurrentHourAggregatedTagLikeDTO currentHourAggregatedTagLikeDTO) {
        return CommandLine.Help.Ansi.AUTO.string(String.format(
                "@|bold,underline %s|@: @|italic %s|@",
                currentHourAggregatedTagLikeDTO.getTag(),
                currentHourAggregatedTagLikeDTO.getHourlyLikes()
        ));
    }
}