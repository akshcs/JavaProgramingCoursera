package org.example.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan({"org.example.service","org.example.client","org.example.controller","org.example.util"})
public class EarthQuakeApplication {
    public static void main(String[] args) {
        SpringApplication.run(EarthQuakeApplication.class, args);
    }
}