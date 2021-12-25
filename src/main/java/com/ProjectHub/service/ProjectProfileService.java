package com.ProjectHub.service;

import com.ProjectHub.entities.Project;
import com.ProjectHub.entities.StudentProfile;
import com.ProjectHub.entities.TeacherProfile;
import com.ProjectHub.model.ProjectDetails;
import com.ProjectHub.model.ProjectProfileModel;
import com.ProjectHub.model.ProjectSubmissionModel;
import com.ProjectHub.repository.ProjectRepository;
import com.ProjectHub.repository.TeacherRepository;
import com.ProjectHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProjectProfileService {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeacherRepository teacherRepository;

    ProjectProfileModel getProjectCardDetails(Long projectID) {
        ProjectProfileModel projectProfileModel = new ProjectProfileModel();
        Project project = projectRepository.findByProjectId(projectID);

        if (project != null) {
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

    private Map<String, String> mapStudentDetails(StudentProfile studentProfile) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", String.valueOf(studentProfile.getUsername()));
        map.put("name", studentProfile.getFirstName() + " " + studentProfile.getLastName());
        return map;
    }

    public ProjectDetails getProjectDetails(Long id) {
        Project project = projectRepository.findByProjectId(id);

        TeacherProfile guide = teacherRepository.findById(String.valueOf(project.getGuideID())).get();
        String guideName = guide.getFirstName() + " " + guide.getLastName();

        Map<String, String> guideMap = new HashMap<String, String>();
        guideMap.put("id", String.valueOf(project.getGuideID()));
        guideMap.put("name", guideName);

        StudentProfile student1 = userRepository.findByUsername(String.valueOf(project.getStudent1id())).get();
        StudentProfile student2 = userRepository.findByUsername(String.valueOf(project.getStudent2id())).get();
        StudentProfile student3 = userRepository.findByUsername(String.valueOf(project.getStudent3id())).get();
        StudentProfile student4 = userRepository.findByUsername(String.valueOf(project.getStudent4id())).get();
        StudentProfile student5 = project.getStudent5id() != null ? userRepository.findByUsername(String.valueOf(project.getStudent5id())).get() : null;

        Map<String, String> map = new HashMap<String, String>();
        map.put("id", null);
        map.put("name", null);

        String[] tokens = project.getProjectInfo().split("#");
        Map<String, String> projectInfo = new HashMap<String, String>();
        projectInfo.put("yearInfo", "EDAI " + tokens[0] + " " + tokens[1]);
        projectInfo.put("groupInfo", tokens[2]);

        ProjectDetails projectDetails = new ProjectDetails(id,
                project.getProjectTitle(),
                project.getProjectDomain(),
                project.getDescription(),
                project.getProjectTag1(), project.getProjectTag2(), project.getProjectTag3(),
                project.getImageUrl(), project.getPptUrl(), project.getPaperUrl(),
                project.getClosed(),
                projectInfo,
                guideMap,
                mapStudentDetails(student1),
                mapStudentDetails(student2),
                mapStudentDetails(student3),
                mapStudentDetails(student4),
                student5 == null ? map : mapStudentDetails(student5),
                project.getDeadline()
        );
        return projectDetails;
    }
}
