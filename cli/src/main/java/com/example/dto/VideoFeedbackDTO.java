package com.example.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import picocli.CommandLine;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Serdeable
@ToString
public class VideoFeedbackDTO {
    private String userId;
    private UUID videoId;
    private boolean likeStatus;

    public static String formatVideoFeedbackDTO(VideoFeedbackDTO videoFeedbackDTO) {
        return CommandLine.Help.Ansi.AUTO.string(String.format(
                "@|bold,underline %s|@ %s @|bold,underline '%s'|@",
                videoFeedbackDTO.getUserId(),
                videoFeedbackDTO.isLikeStatus() ? "liked" : "disliked",
                videoFeedbackDTO.getVideoId()
        ));
    }
}
