package com.ProjectHub.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Avinash Vijayvargiya on 30-09-2021.
 */
@Entity
@Table(name = "studentsuserdetails")
public class User implements Serializable {

    private static final long serialVersionUID = 2364534L;
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "roles")
    private String roles;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return true;
    }

    public String getRoles() {
        return roles;
    }
}

