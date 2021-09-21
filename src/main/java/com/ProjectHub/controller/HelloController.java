package com.ProjectHub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Avinash Vijayvargiya on 21-09-2021.
 */

@RestController
public class HelloController {

    @GetMapping("/helloworld")
    public String hello(){
        return "Hello World";
    }
}
