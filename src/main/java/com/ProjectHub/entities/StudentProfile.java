package com.ProjectHub.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Avinash Vijayvargiya on 30-09-2021.
 */
@Entity
@Table(name = "studentsuserdetails")
public class StudentProfile implements Serializable {

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
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

