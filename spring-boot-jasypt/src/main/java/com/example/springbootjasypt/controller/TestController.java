package com.example.springbootjasypt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${test.value}")
    private String TEST_VALUE;

    @GetMapping
    public String test1() {
        return TEST_VALUE;
    }
}
