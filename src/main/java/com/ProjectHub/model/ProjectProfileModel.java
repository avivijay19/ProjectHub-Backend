package com.ProjectHub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
This project model is for project cards on profile page
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectProfileModel {
    private Long projectId;
    private String projectTitle;
    private String projectDomain;
    private String description;
    private String projectTag1;
    private String projectTag2;
    private String projectTag3;
}
