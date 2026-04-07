package com.ameed.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class Demo1Application {
    static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }
}
