package com.ProjectHub.controller;

import com.ProjectHub.model.ChangePasswordModel;
import com.ProjectHub.model.security.JwtAuthenticationToken;
import com.ProjectHub.model.security.JwtUser;
import com.ProjectHub.service.AuthService;
import com.ProjectHub.util.Endpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Avinash Vijayvargiya on 02-10-2021.
 */
@RestController
@RequestMapping(Endpoints.AUTH)
public class AuthenticationController {

    @Autowired
    private AuthService authService;


    @PostMapping("/studentLogin")
    public ResponseEntity<JwtAuthenticationToken> studentLogin(@RequestBody final JwtUser jwtUser) {
        try {
            JwtAuthenticationToken jwtAuthenticationToken = authService.studentLogin(jwtUser);
            if (jwtAuthenticationToken != null) {
                return new ResponseEntity<>(jwtAuthenticationToken, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            System.out.println("studentLogin: " + e);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/teacherLogin")
    public ResponseEntity<JwtAuthenticationToken> teacherLogin(@RequestBody final JwtUser jwtUser) {
        try {
            JwtAuthenticationToken jwtAuthenticationToken = authService.teacherLogin(jwtUser);
            if (jwtAuthenticationToken != null) {
                return new ResponseEntity<>(jwtAuthenticationToken, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            System.out.println("teacherLogin: " + e);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/adminLogin")
    public ResponseEntity<JwtAuthenticationToken> adminLogin(@RequestBody final JwtUser jwtUser) {
        try {
            JwtAuthenticationToken jwtAuthenticationToken = authService.adminLogin(jwtUser);
            if (jwtAuthenticationToken != null) {
                return new ResponseEntity<>(jwtAuthenticationToken, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            System.out.println("adminLogin: " + e);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/changePassword")
    public ResponseEntity<HttpStatus> changePassword(@RequestBody ChangePasswordModel changePasswordModel) {
        try {
            boolean status = authService.changePassword(changePasswordModel);
            if (status) return new ResponseEntity<>(HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
