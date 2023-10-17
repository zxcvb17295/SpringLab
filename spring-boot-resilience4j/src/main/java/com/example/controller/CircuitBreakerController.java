package com.example.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/circuit-breaker")
    @CircuitBreaker(name = "CB1")
    public String circuitBreaker() {
        return restTemplate.getForObject("/error", String.class);
    }
}
