package com.ProjectHub.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Avinash Vijayvargiya on 22-09-2021.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long projectId;

    @Column(name = "projectTitle", nullable = false)
    private String projectTitle;

    @Column(name = "projectDomain", nullable = false)
    private String projectDomain;

    @Column(name = "projectDescription", nullable = false)
//    @Size(max = 250)
    private String description;

    @Column(name = "projectTag1", nullable = false)
    private String projectTag1;

    @Column(name = "projectTag2")
    private String projectTag2;

    @Column(name = "projectTag3")
    private String projectTag3;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "pptUrl")
    private String pptUrl;

    @Column(name = "paperUrl")
    private String paperUrl;

    @Column(name = "approvedByTeacher", columnDefinition = "0")
    private int approvedByTeacher;
}