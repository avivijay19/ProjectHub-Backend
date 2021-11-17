package com.ProjectHub.service;

import com.ProjectHub.entities.Project;
import com.ProjectHub.model.ProjectProfileModel;
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
}
