package com.example.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import picocli.CommandLine;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Serdeable
@ToString
public class VideoDTO {
    private String userId;
    private UUID videoId;
    private String title;
    private Set<String> tags;

    public static String formatVideoDTO(VideoDTO videoDTO) {
        return CommandLine.Help.Ansi.AUTO.string(String.format(
                "@|bold,underline %s|@ posted @|bold,underline '%s'|@ @|bold (%s)|@ with tags: @|italic %s|@",
                videoDTO.getUserId(),
                videoDTO.getTitle(),
                videoDTO.getVideoId(),
                videoDTO.getTags()
        ));
    }
}

