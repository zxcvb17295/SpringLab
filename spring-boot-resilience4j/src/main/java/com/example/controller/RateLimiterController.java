package com.example.controller;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimiterController {

    @GetMapping("/rate-limiter")
    @RateLimiter(name = "RA1")
    public String rateLimiter() {
        return "RateLimiter";
    }
}
