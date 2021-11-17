package com.ProjectHub.service;

import com.ProjectHub.entities.Project;
import com.ProjectHub.entities.StudentProfile;
import com.ProjectHub.exceptions.ResourceNotFoundException;
import com.ProjectHub.model.ProjectProfileModel;
import com.ProjectHub.model.StudentProfileModel;
import com.ProjectHub.repository.ProjectRepository;
import com.ProjectHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


/**
 * Created by Avinash Vijayvargiya on 11/8/2021.
 */
@Service
public class StudentProfileService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProjectProfileService projectProfileService;

    public StudentProfileModel getStudentProfileByUsername(String username){
        StudentProfileModel studentProfileModel = new StudentProfileModel();
        StudentProfile studentProfile = userRepository.findByUsername(username).
                orElseThrow(() -> new ResourceNotFoundException("Student with id: " + username + " not found."));
        studentProfileModel.setUsername(studentProfile.getUsername());
        studentProfileModel.setFirstName(studentProfile.getFirstName());
        studentProfileModel.setLastName(studentProfile.getLastName());
        studentProfileModel.setEmailId(studentProfile.getEmailId());
        studentProfileModel.setDepartment(studentProfile.getDepartment());

        ArrayList<ProjectProfileModel> projects = new ArrayList<ProjectProfileModel>() {
            {
                add(projectProfileService.getProjectCardDetails(studentProfile.getProject1id()));
                add(projectProfileService.getProjectCardDetails(studentProfile.getProject2id()));
                add(projectProfileService.getProjectCardDetails(studentProfile.getProject3id()));
                add(projectProfileService.getProjectCardDetails(studentProfile.getProject4id()));
                add(projectProfileService.getProjectCardDetails(studentProfile.getProject5id()));
                add(projectProfileService.getProjectCardDetails(studentProfile.getProject6id()));
                add(projectProfileService.getProjectCardDetails(studentProfile.getProject7id()));
                add(projectProfileService.getProjectCardDetails(studentProfile.getProject8id()));
            }
        };

        studentProfileModel.setProjects(projects);
        return studentProfileModel;
    }
}