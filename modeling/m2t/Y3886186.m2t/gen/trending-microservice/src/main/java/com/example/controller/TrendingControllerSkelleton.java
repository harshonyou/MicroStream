// Code Generated Via EGL Template

package com.example.controller;

import io.micronaut.http.annotation.*;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import javax.inject.Inject;

@Controller("/trending")
public class TrendingController {
    @Get("/health")
    public HttpResponse<String> health() {
        // TODO: Implement the logic for health
        return HttpResponse.status(HttpStatus.OK).body("Success");
    }

    @Get("/hashtags/top/current")
    public HttpResponse<String> hashtagstopcurrent() {
        // TODO: Implement the logic for hashtagstopcurrent
        return HttpResponse.status(HttpStatus.OK).body("Success");
    }

    @Get("/hashtags/top/past")
    public HttpResponse<String> hashtagstoppast() {
        // TODO: Implement the logic for hashtagstoppast
        return HttpResponse.status(HttpStatus.OK).body("Success");
    }

}
