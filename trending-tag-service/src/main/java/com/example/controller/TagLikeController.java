package com.example.controller;

import com.example.model.HourlyLike;
import com.example.model.TagLike;
import com.example.model.TagLikeDTO;
import com.example.repository.TagLikeRepository;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/tag-like")
public class TagLikeController {

    @Inject
    TagLikeRepository tagLikeRepository;

    @Post("/")
    public void likeTag(@Body TagLike tag) {
        tagLikeRepository.save(tag);
    }

    @Get("/")
    public Iterable<TagLike> getTags() {
        return tagLikeRepository.findAll();
    }

    @Get("/top-10")
    public Iterable<TagLikeDTO> getTop10Tags() {
        return tagLikeRepository.findTopTagsByHourlyLikes();
    }
}