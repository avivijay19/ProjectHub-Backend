package com.ProjectHub.model;

import com.ProjectHub.entities.TeacherProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherProfileModel {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String department;
    private String emailId;
    private List<ProjectProfileModel> projects;

    public TeacherProfileModel(TeacherProfile teacherProfile, List<ProjectProfileModel> projectProfileModel) {
        setEmployeeId(teacherProfile.getEmployeeID());
        setFirstName(teacherProfile.getFirstName());
        setLastName(teacherProfile.getLastName());
        setDepartment(teacherProfile.getDepartment());
        setEmailId(teacherProfile.getEmailId());
        setProjects(projectProfileModel);
    }
}