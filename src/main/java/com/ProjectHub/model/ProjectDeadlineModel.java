package com.ProjectHub.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ProjectDeadlineModel {
    Long projectID;
    LocalDate deadline;
}
