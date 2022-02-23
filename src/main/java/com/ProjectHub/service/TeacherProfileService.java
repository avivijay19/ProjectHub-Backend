package com.ProjectHub.service;

import com.ProjectHub.documents.Project;
import com.ProjectHub.documents.TeacherProfile;
import com.ProjectHub.model.ProjectProfileModel;
import com.ProjectHub.model.TeacherProfileModel;
import com.ProjectHub.repository.ProjectRepository;
import com.ProjectHub.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherProfileService {
    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    ProjectRepository projectRepository;

    public TeacherProfileModel getTeacherProfileByUsername(String username) {
        TeacherProfile teacherProfile = teacherRepository.findById(username).get();
        List<Project> projectList = projectRepository.findAllByTeacherId(Long.valueOf(username), "1");

        List<ProjectProfileModel> projectProfileModelList = new ArrayList<>();
        for (Project project : projectList) {
            projectProfileModelList.add(new ProjectProfileModel(
                    project.getProjectId(),
                    project.getProjectTitle(),
                    project.getProjectDomain(),
                    project.getDescription(),
                    project.getProjectTag1(),
                    project.getProjectTag2(),
                    project.getProjectTag3()));
        }

        return new TeacherProfileModel(teacherProfile, projectProfileModelList);
    }
}