package com.ProjectHub.service;

import com.ProjectHub.entities.Project;
import com.ProjectHub.entities.TeacherProfile;
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
        for (int i = 0; i < projectList.size(); i++) {
            projectProfileModelList.add(new ProjectProfileModel(
                    projectList.get(i).getProjectId(),
                    projectList.get(i).getProjectTitle(),
                    projectList.get(i).getProjectDomain(),
                    projectList.get(i).getDescription(),
                    projectList.get(i).getProjectTag1(),
                    projectList.get(i).getProjectTag2(),
                    projectList.get(i).getProjectTag3()));
        }

        return new TeacherProfileModel(teacherProfile, projectProfileModelList);
    }
}