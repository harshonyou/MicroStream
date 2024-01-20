// Code Generated Via EGL Template

package com.example.dto;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Serdeable
public class TagEngagementEventDTO {
    private String userId;
    private String tag;
    private boolean subscriptionStatus;
}
