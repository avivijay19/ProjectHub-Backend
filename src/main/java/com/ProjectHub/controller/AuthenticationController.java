package com.ProjectHub.controller;

import com.ProjectHub.model.ChangePasswordModel;
import com.ProjectHub.model.security.JwtUser;
import com.ProjectHub.security.jwt.JwtGenerator;
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

    @Autowired
    private JwtGenerator jwtGenerator;

    @PostMapping("/studentLogin")
    public ResponseEntity studentLogin(@RequestBody final JwtUser jwtUser) {

        // check if user is in database
        if (jwtUser.getUsername().equals("admin") && jwtUser.getPassword().equals("1234")) {
            jwtUser.setRole("ROLE_ADMIN");
            jwtUser.setId(1l);
            return ResponseEntity.ok().header("MyToken", jwtGenerator.generate(jwtUser)).body(null);
        } else if (jwtUser.getUsername().equals("user") && jwtUser.getPassword().equals("1234")) {
            jwtUser.setRole("ROLE_STUDENT");
            jwtUser.setId(2l);
            return ResponseEntity.ok().header("MyToken", jwtGenerator.generate(jwtUser)).body(null);
        } else {
            return ResponseEntity.badRequest().body(null);
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
