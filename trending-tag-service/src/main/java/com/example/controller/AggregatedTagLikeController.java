package com.example.controller;

import com.example.model.AggregatedTagLike;
import com.example.dto.AggregatedTagLikeDTO;
import com.example.repository.AggregatedTagLikeRepository;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/tag-like")
public class AggregatedTagLikeController {

    @Inject
    AggregatedTagLikeRepository tagLikeRepository;

    @Post("/")
    public void likeTag(@Body AggregatedTagLike tag) {
        tagLikeRepository.save(tag);
    }

    @Get("/")
    public Iterable<AggregatedTagLike> getTags() {
        return tagLikeRepository.findAll();
    }

    @Get("/top-10")
    public Iterable<AggregatedTagLikeDTO> getTop10Tags() {
//        List<AggregatedTagLikeDTO> topTen = tagLikeRepository.findTopTagsByHourlyLikes();
//        topTen.forEach(System.out::println);
        return tagLikeRepository.findTopTagsByHourlyLikes();
    }
}