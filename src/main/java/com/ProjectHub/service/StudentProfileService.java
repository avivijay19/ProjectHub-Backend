package com.ProjectHub.service;

import com.ProjectHub.documents.StudentProfile;
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

    private Boolean isOngoing(Long id) {
        LocalDate deadline = projectRepository.findByProjectId(id).getDeadline();
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Kolkata"));
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
        studentProfileModel.setPersonalEmail(studentProfile.getPersonalEmail());
        studentProfileModel.setDepartment(studentProfile.getDepartment());

        ArrayList<ProjectProfileModel> projects = new ArrayList<ProjectProfileModel>() {
            {
                if (studentProfile.getProject1id() != null && !isOngoing(studentProfile.getProject1id()))
                    add(projectProfileService.getProjectCardDetails(studentProfile.getProject1id()));
                else add(null);
                if (studentProfile.getProject2id() != null && !isOngoing(studentProfile.getProject2id()))
                    add(projectProfileService.getProjectCardDetails(studentProfile.getProject2id()));
                else add(null);
                if (studentProfile.getProject3id() != null && !isOngoing(studentProfile.getProject3id()))
                    add(projectProfileService.getProjectCardDetails(studentProfile.getProject3id()));
                else add(null);
                if (studentProfile.getProject4id() != null && !isOngoing(studentProfile.getProject4id()))
                    add(projectProfileService.getProjectCardDetails(studentProfile.getProject4id()));
                else add(null);
                if (studentProfile.getProject5id() != null && !isOngoing(studentProfile.getProject5id()))
                    add(projectProfileService.getProjectCardDetails(studentProfile.getProject5id()));
                else add(null);
                if (studentProfile.getProject6id() != null && !isOngoing(studentProfile.getProject6id()))
                    add(projectProfileService.getProjectCardDetails(studentProfile.getProject6id()));
                else add(null);
                if (studentProfile.getProject7id() != null && !isOngoing(studentProfile.getProject7id()))
                    add(projectProfileService.getProjectCardDetails(studentProfile.getProject7id()));
                else add(null);
                if (studentProfile.getProject8id() != null && !isOngoing(studentProfile.getProject8id()))
                    add(projectProfileService.getProjectCardDetails(studentProfile.getProject8id()));
                else add(null);
            }
        };

        studentProfileModel.setProjects(projects);
        return studentProfileModel;
    }

    public Long getOngoingID(String username) {
        StudentProfile studentProfile = userRepository.findByUsername(username).
                orElseThrow(() -> new ResourceNotFoundException("Student with id: " + username + " not found."));

        ArrayList<Long> projects = new ArrayList<Long>() {
            {
                if (studentProfile.getProject1id() != null)
                    add(studentProfile.getProject1id());
                if (studentProfile.getProject2id() != null)
                    add(studentProfile.getProject2id());
                if (studentProfile.getProject3id() != null)
                    add(studentProfile.getProject3id());
                if (studentProfile.getProject4id() != null)
                    add(studentProfile.getProject4id());
                if (studentProfile.getProject5id() != null)
                    add(studentProfile.getProject5id());
                if (studentProfile.getProject6id() != null)
                    add(studentProfile.getProject6id());
                if (studentProfile.getProject7id() != null)
                    add(studentProfile.getProject7id());
                if (studentProfile.getProject8id() != null)
                    add(studentProfile.getProject8id());
            }
        };

        LocalDate today = LocalDate.now(ZoneId.of("Asia/Kolkata"));
        for (int i = projects.size() - 1; i >= 0; i--) {
            if (isOngoing(projects.get(i))) {
                return projects.get(i);
            }
        }

        return null;
    }
}