package com.ProjectHub.model;

import com.ProjectHub.entities.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/*
This project model is for teacher ongoing project cards
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherOngoingProjectModel {
    private long projectId;
    private String projectTitle;
    private Map<String, String> projectInfo = new HashMap<String, String>();

    public TeacherOngoingProjectModel(Project project) {
        setProjectId(project.getProjectId());
        setProjectTitle(project.getProjectTitle());
        String[] tokens = project.getProjectInfo().split("#");
        projectInfo.put("yearInfo", tokens[0] + " " + tokens[1]);
        projectInfo.put("groupInfo", tokens[2]);
    }
}
