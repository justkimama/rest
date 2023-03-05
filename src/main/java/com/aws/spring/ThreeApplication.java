package com.aws.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.aws.spring.controller"})
public class ThreeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ThreeApplication.class, args);
    }
}
