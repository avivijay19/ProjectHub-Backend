package com.ProjectHub.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Avinash Vijayvargiya on 30-09-2021.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "department")
    private String department;

    @Column(name = "emailId")
    private String emailId;

    @Column(name = "personalEmail")
    private String personalEmail;

    @Column(name = "roles")
    private String roles;

    //    Projects

    @Column(name = "project1id")
    private Long project1id;

    @Column(name = "project2id")
    private Long project2id;

    @Column(name = "project3id")
    private Long project3id;

    @Column(name = "project4id")
    private Long project4id;

    @Column(name = "project5id")
    private Long project5id;

    @Column(name = "project6id")
    private Long project6id;

    @Column(name = "project7id")
    private Long project7id;

    @Column(name = "project8id")
    private Long project8id;

    //    public String getUsername() {
//        return username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
    public boolean isActive() {
        return true;
    }
//
//    public String getRoles() {

    public StudentProfile(String username, String password, String firstName, String lastName, String department, String emailId, String personalEmail, String roles) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.emailId = emailId;
        this.personalEmail = personalEmail;
        this.roles = roles;
    }
//        return roles;
//    }
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }


}

