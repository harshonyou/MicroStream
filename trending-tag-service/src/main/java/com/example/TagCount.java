package com.example;

import lombok.*;

@Data
@AllArgsConstructor
public class TagCount {
    private String hashtag;
    private long count;
}
