package com.ProjectHub.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * Created by Avinash Vijayvargiya on 22-09-2021.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("project")
public class Project {

    @Id
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
    private String closed = "0";
    private String projectInfo;

    // Team and Guide
    private Long guideID;
    private Long student1id;
    private Long student2id;
    private Long student3id;
    private Long student4id;
    private Long student5id;

    // Date
    private LocalDate deadline;
}