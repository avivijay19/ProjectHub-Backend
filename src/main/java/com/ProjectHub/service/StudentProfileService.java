package com.ProjectHub.service;

import com.ProjectHub.entities.StudentProfile;
import com.ProjectHub.model.StudentProfileModel;
import com.ProjectHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Avinash Vijayvargiya on 11/8/2021.
 */
@Service
public class StudentProfileService {
    @Autowired
    UserRepository userRepository;

    public StudentProfileModel getStudentProfileByUsername(String username){
        StudentProfileModel studentProfileModel = new StudentProfileModel();
        StudentProfile studentProfile =  userRepository.findByUsername(username);
        studentProfileModel.setFirstName(studentProfile.getFirstName());
        studentProfileModel.setLastName(studentProfile.getLastName());
        return studentProfileModel;
    }
}
