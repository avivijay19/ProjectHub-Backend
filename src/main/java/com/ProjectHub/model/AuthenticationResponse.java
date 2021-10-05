package com.ProjectHub.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Avinash Vijayvargiya on 30-09-2021.
 */

@Data
@AllArgsConstructor
public class AuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 345L;

    private final String jwt;
    private String username;
    private String firstName;
    private String lastName;
    private List<String> roles;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }
}

