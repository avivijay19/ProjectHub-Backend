package com.ProjectHub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetails {
    private long projectId;
    private String projectTitle;
    private String projectDomain;
    private String description;
    private String projectTag1;
    private String projectTag2;
    private String projectTag3;
    private String imageUrl;
    private String pptUrl;
    private String paperUrl;
    private String closed;
    private Map<String, String> projectInfo;

    // Team and Guide

    private Map<String, String> guide;

    private Map<String, String> student1;
    private Map<String, String> student2;
    private Map<String, String> student3;
    private Map<String, String> student4;
    private Map<String, String> student5;

    // Date
    private LocalDate deadline;
}
