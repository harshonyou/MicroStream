// Code Generated Via EGL Template

package com.example.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/api/v1")
public class HealthController {

    @Get(value = "/health")
    public HttpResponse<String> health() {
        return HttpResponse.ok("OK");
    }
}
