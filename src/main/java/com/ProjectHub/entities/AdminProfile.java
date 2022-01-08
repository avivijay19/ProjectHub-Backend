package com.ProjectHub.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admin")
public class AdminProfile {
    @Id
    @Column(name = "username")
    private String usernameAdmin;

    @Column(name = "password")
    private String passwordAdmin;

    @Column(name = "firstName")
    private String firstNameAdmin;

    @Column(name = "lastName")
    private String lastNameAdmin;

    @Column(name = "department")
    private String departmentAdmin;

    @Column(name = "emailId")
    private String emailIdAdmin;

    @Column(name = "roles")
    private String rolesAdmin;

    public boolean isActive() {
        return true;
    }
}
