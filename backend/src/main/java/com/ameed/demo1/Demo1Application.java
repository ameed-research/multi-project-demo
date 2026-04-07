package com.ameed.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo1Application {

    public static void main(String[] args) {
        System.out.println("DEBUG: STORAGE_ACCOUNT_NAME = " + System.getenv("STORAGE_ACCOUNT_NAME"));
        SpringApplication.run(Demo1Application.class, args);
    }

}
