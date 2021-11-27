package com.ProjectHub.service;

import com.ProjectHub.entities.Project;
import com.ProjectHub.model.ProjectProfileModel;
import com.ProjectHub.model.ProjectSubmissionModel;
import com.ProjectHub.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectProfileService {
    @Autowired
    ProjectRepository projectRepository;

    ProjectProfileModel getProjectCardDetails(Long projectID){
        ProjectProfileModel projectProfileModel = new ProjectProfileModel();
        Project project = projectRepository.findByProjectId(projectID);

        if(project!=null){
            projectProfileModel.setProjectID(project.getProjectId());
            projectProfileModel.setProjectDomain(project.getProjectDomain());
            projectProfileModel.setProjectTitle(project.getProjectTitle());
            projectProfileModel.setDescription(project.getDescription());
            projectProfileModel.setProjectTag1(project.getProjectTag1());
            projectProfileModel.setProjectTag2(project.getProjectTag2());
            projectProfileModel.setProjectTag3(project.getProjectTag3());
            return projectProfileModel;
        }
        return null;
    }

    public Project updateProject(ProjectSubmissionModel _project) {
        Project project = projectRepository.findByProjectId(_project.getProjectID());
        project.setProjectTitle(_project.getProjectTitle());
        project.setDescription(_project.getDescription());
        project.setProjectDomain(_project.getProjectDomain());
        project.setImageUrl(_project.getImageUrl());
        project.setPptUrl(_project.getPptUrl());
        project.setPaperUrl(_project.getPaperUrl());
        project.setProjectTag1(_project.getProjectTag1());
        project.setProjectTag2(_project.getProjectTag2());
        project.setProjectTag3(_project.getProjectTag3());
        projectRepository.save(project);
        return project;
    }
}
