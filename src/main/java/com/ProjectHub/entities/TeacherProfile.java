package com.ProjectHub.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teacherdetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherProfile {
    private static final long serialVersionUID = 2364535L;

    @Id
    @Column(name = "employeeID")
    private String employeeID;

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

    @Column(name = "roles")
    private String roles;

    public boolean isActive() {
        return true;
    }
}