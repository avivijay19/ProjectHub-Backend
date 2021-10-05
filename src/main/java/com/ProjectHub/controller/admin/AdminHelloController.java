package com.ProjectHub.controller.admin;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ProjectHub.util.Constants.BEARER_AUTH;


/**
 * Created by Avinash Vijayvargiya on 21-09-2021.
 */

@RestController
public class AdminHelloController {
//    @SecurityRequirement(name = BEARER_AUTH)
    @GetMapping("admin/helloworld")
    public String helloAdmin(){
        return "Hello World Admin";
    }

//    @SecurityRequirement(name = BEARER_AUTH)
    @GetMapping("teacher/helloworld")
    public String helloTeacher(){
        return "Hello World Teacher";
    }

//    @SecurityRequirement(name = BEARER_AUTH)
    @GetMapping("student/helloworld")
    public String helloStudent(){
        return "Hello World Student";
    }
}