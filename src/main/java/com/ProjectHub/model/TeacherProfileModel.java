package com.ProjectHub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherProfileModel {
    private String employeeID;
    private String password;
    private String firstName;
    private String lastName;
    private String department;
    private String emailId;
}