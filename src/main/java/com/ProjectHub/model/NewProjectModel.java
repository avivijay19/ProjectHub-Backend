package com.ProjectHub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewProjectModel {
    private String projectInfo;
    private Long guideID;
    private Long student1ID;
    private Long student2ID;
    private Long student3ID;
    private Long student4ID;
    private Long student5ID;
    private LocalDate deadline;
}
