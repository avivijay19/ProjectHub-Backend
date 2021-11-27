package com.ProjectHub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectProfileModel {
    private Long projectID;
    private String projectTitle;
    private String projectDomain;
    private String description;
    private String projectTag1;
    private String projectTag2;
    private String projectTag3;
}
