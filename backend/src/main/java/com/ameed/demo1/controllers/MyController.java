package com.ameed.demo1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MyController {

    @GetMapping
    public Map<String, Object> hi() {
        return Map.of("message", "Hello World");
    }
}
