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

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;


/**
 * Created by Avinash Vijayvargiya on 11/8/2021.
 */
@Service
public class StudentProfileService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectProfileService projectProfileService;

    private Boolean isOngoing(LocalDate today, LocalDate deadline) {
        return Period.between(today, deadline).getDays() >= 0;
    }

    public StudentProfileModel getStudentProfileByUsername(String username) {
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

    public Long getOngoingID(String username) {
        StudentProfile studentProfile = userRepository.findByUsername(username).
                orElseThrow(() -> new ResourceNotFoundException("Student with id: " + username + " not found."));

        ArrayList<Project> projects = new ArrayList<Project>() {
            {
                if (studentProfile.getProject1id() != null)
                    add(projectRepository.findByProjectId(studentProfile.getProject1id()));
                if (studentProfile.getProject2id() != null)
                    add(projectRepository.findByProjectId(studentProfile.getProject2id()));
                if (studentProfile.getProject3id() != null)
                    add(projectRepository.findByProjectId(studentProfile.getProject3id()));
                if (studentProfile.getProject4id() != null)
                    add(projectRepository.findByProjectId(studentProfile.getProject4id()));
                if (studentProfile.getProject5id() != null)
                    add(projectRepository.findByProjectId(studentProfile.getProject5id()));
                if (studentProfile.getProject6id() != null)
                    add(projectRepository.findByProjectId(studentProfile.getProject6id()));
                if (studentProfile.getProject7id() != null)
                    add(projectRepository.findByProjectId(studentProfile.getProject7id()));
                if (studentProfile.getProject8id() != null)
                    add(projectRepository.findByProjectId(studentProfile.getProject8id()));
            }
        };

        LocalDate today = LocalDate.now(ZoneId.of("Asia/Kolkata"));
        for (int i = projects.size() - 1; i >= 0; i--) {
            if (isOngoing(today, projects.get(i).getDeadline())) {
                return projects.get(i).getProjectId();
            }
        }

        return null;
    }
}