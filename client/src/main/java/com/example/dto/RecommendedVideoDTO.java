package com.example.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import picocli.CommandLine;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Serdeable
public class RecommendedVideoDTO {
    private UUID id;
    private String title;
    // Subset of tags that the recommended video is affiliated with
    private Set<String> affiliatedTags;
    private Long views;

    public static String formatRecommendedVideoDTO(RecommendedVideoDTO recommendedVideoDTO) {
        return CommandLine.Help.Ansi.AUTO.string(String.format(
                "@|bold,underline %s|@: @|italic %s|@ (@|yellow %s|@) - @|green %d|@ views",
                recommendedVideoDTO.getId(),
                recommendedVideoDTO.getTitle(),
                String.join(", ", recommendedVideoDTO.getAffiliatedTags()),
                recommendedVideoDTO.getViews()
        ));
    }
}