package com.ProjectHub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * Created by Avinash Vijayvargiya on 11/8/2021.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentProfileModel {
    private String username;
    private String firstName;
    private String lastName;
    private String emailId;
    private String personalEmail;
    private String department;
    ArrayList<ProjectProfileModel> projects;
}
