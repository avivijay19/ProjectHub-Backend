package com.ProjectHub.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Created by Avinash Vijayvargiya on 30-09-2021.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("studentsuserdetails")
public class StudentProfile {
    @Id
    private String username;

    private String password;
    private String firstName;
    private String lastName;
    private String department;
    private String emailId;
    private String personalEmail;
    private String roles;
    private Long project1id;
    private Long project2id;
    private Long project3id;
    private Long project4id;
    private Long project5id;
    private Long project6id;
    private Long project7id;
    private Long project8id;

    public boolean isActive() {
        return true;
    }

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
}

