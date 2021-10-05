package com.ProjectHub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Avinash Vijayvargiya on 02-10-2021.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest implements Serializable {
    private String username;
    private String password;
}
