package com.example.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import picocli.CommandLine;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Serdeable
@ToString
public class VideoEngagementDTO {
    private String userId;
    private UUID videoId;
    private Instant watchedTime;

    public static String formatVideoEngagementDTO(VideoEngagementDTO videoEngagementDTO) {
        return CommandLine.Help.Ansi.AUTO.string(String.format(
                "@|bold,underline %s|@ watched @|bold,underline '%s'|@ at @|italic %s|@",
                videoEngagementDTO.getUserId(),
                videoEngagementDTO.getVideoId(),
                videoEngagementDTO.getWatchedTime()
        ));
    }
}
