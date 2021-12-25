package com.ProjectHub.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Avinash Vijayvargiya on 22-09-2021.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long projectId;

    @Column(name = "projectTitle")
    private String projectTitle;

    @Column(name = "projectDomain")
    private String projectDomain;

    @Column(name = "projectDescription")
//    @Size(max = 250)
    private String description;

    @Column(name = "projectTag1")
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

    @Column(name = "closed", columnDefinition = "varchar(255) default '0'")
    private String closed;

    @Column(name = "projectInfo")
    private String projectInfo;

    // Team and Guide

    @Column(name = "guideID")
    private Long guideID;

    @Column(name = "student1id", nullable = false)
    private Long student1id;

    @Column(name = "student2id", nullable = false)
    private Long student2id;

    @Column(name = "student3id", nullable = false)
    private Long student3id;

    @Column(name = "student4id", nullable = false)
    private Long student4id;

    @Column(name = "student5id")
    private Long student5id;

    // Date
    @Column(name = "deadline")
    private LocalDate deadline;
}