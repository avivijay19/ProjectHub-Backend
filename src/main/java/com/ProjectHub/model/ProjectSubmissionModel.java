package com.ProjectHub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
This project model is for project submission page
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectSubmissionModel {
    private Long projectID;
    private String projectTitle;
    private String projectDomain;
    private String description;
    private String projectTag1;
    private String projectTag2;
    private String projectTag3;
    private String imageUrl;
    private String pptUrl;
    private String paperUrl;
}
