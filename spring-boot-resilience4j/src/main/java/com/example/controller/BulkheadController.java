package com.example.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class BulkheadController {

    @GetMapping("/bulkhead")
    @Bulkhead(name="BK1")
    public String bulkhead() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return "bulkhead";
    }
}
