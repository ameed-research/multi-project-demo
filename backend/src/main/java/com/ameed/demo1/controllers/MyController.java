package com.ameed.demo1.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class MyController {

    @GetMapping
    public Map<String, Object> hi() {
        return Map.of("message", "Hello World");
    }
}
