package com.ProjectHub.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Avinash Vijayvargiya on 22-09-2021.
 */

@Data
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

    @Column(name = "approvedByTeacher")
    private String approvedByTeacher;

    public Project(String projectTitle, String projectDomain, String description, String projectTag1, String projectTag2, String projectTag3, String imageUrl, String pptUrl, String paperUrl, String approvedByTeacher) {
        this.projectTitle = projectTitle;
        this.projectDomain = projectDomain;
        this.description = description;
        this.projectTag1 = projectTag1;
        this.projectTag2 = projectTag2;
        this.projectTag3 = projectTag3;
        this.imageUrl = imageUrl;
        this.pptUrl = pptUrl;
        this.paperUrl = paperUrl;
        this.approvedByTeacher = approvedByTeacher;
    }
}