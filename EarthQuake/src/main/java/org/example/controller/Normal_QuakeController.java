package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Quakes/")
public class Normal_QuakeController {

    @GetMapping("")
    String home() {
        return "Hello Folks Welcome to Programming and Interfaces Searching Earthquake Data Programming Exercise Answers";
    }
}
