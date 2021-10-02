package com.ProjectHub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Avinash Vijayvargiya on 02-10-2021.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "userDetails")
public class UserDetails {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "roles")
    private String roles;

}
