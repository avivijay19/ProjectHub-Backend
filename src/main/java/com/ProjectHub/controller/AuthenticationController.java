package com.ProjectHub.controller;

import com.ProjectHub.model.AuthenticationRequest;
import com.ProjectHub.model.AuthenticationResponse;
import com.ProjectHub.model.ChangePasswordModel;
import com.ProjectHub.model.MyUserDetails;
import com.ProjectHub.service.AuthService;
import com.ProjectHub.service.JPAUserDetailsService;
import com.ProjectHub.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Avinash Vijayvargiya on 02-10-2021.
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private JPAUserDetailsService userDetailsService;

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/studentLogin")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            HttpHeaders responseHeaders = new HttpHeaders();

            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
            final MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String jwt = jwtTokenUtil.generateToken(userDetails);
            AuthenticationResponse response = new AuthenticationResponse(jwt);

            response.setUsername(userDetails.getUsername());
            List<String> roles = new ArrayList<>();
            userDetails.getAuthorities().forEach((a) -> roles.add(a.getAuthority()));
            response.setRoles(roles);

            return new ResponseEntity<>(response, responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping(value = "/teacherLogin")
    public ResponseEntity<?> createAuthenticationTokenTeacher(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            HttpHeaders responseHeaders = new HttpHeaders();
//            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
            final MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByEmployeeId(authenticationRequest.getUsername());
            final String jwt = jwtTokenUtil.generateToken(userDetails);
            AuthenticationResponse response = new AuthenticationResponse(jwt);

            response.setUsername(userDetails.getUsername());
            response.setUsername(userDetails.getFirstName());
            response.setUsername(userDetails.getLastName());
            List<String> roles = new ArrayList<>();
            userDetails.getAuthorities().forEach((a) -> roles.add(a.getAuthority()));
            response.setRoles(roles);

            return new ResponseEntity<>(response, responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping(value = "/adminLogin")
    public ResponseEntity<?> createAuthenticationTokenAdmin(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            HttpHeaders responseHeaders = new HttpHeaders();
//            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
            final MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsernameAdmin(authenticationRequest.getUsername());
            final String jwt = jwtTokenUtil.generateToken(userDetails);
            AuthenticationResponse response = new AuthenticationResponse(jwt);

            response.setUsername(userDetails.getUsername());
            response.setUsername(userDetails.getFirstName());
            response.setUsername(userDetails.getLastName());
            List<String> roles = new ArrayList<>();
            userDetails.getAuthorities().forEach((a) -> roles.add(a.getAuthority()));
            response.setRoles(roles);

            return new ResponseEntity<>(response, responseHeaders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_ACCEPTABLE);
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
