package com.ProjectHub.service;

import com.ProjectHub.entities.StudentProfile;
import com.ProjectHub.entities.TeacherProfile;
import com.ProjectHub.model.ChangePasswordModel;
import com.ProjectHub.repository.TeacherRepository;
import com.ProjectHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TeacherRepository teacherRepository;

    private void updateStudentPassword(String studentId, String newPassword) {
        StudentProfile studentProfile = userRepository.findByUsername(studentId).get();
        studentProfile.setPassword(newPassword);
        userRepository.save(studentProfile);
    }

    private void updateTeacherPassword(String teacherId, String newPassword) {
        TeacherProfile teacherProfile = teacherRepository.findById(teacherId).get();
        teacherProfile.setPassword(newPassword);
        teacherRepository.save(teacherProfile);
    }

    public void changePassword(ChangePasswordModel changePasswordModel) {
        if (Objects.equals(changePasswordModel.getUserType(), "student")) {
            updateStudentPassword(changePasswordModel.getId(), changePasswordModel.getNewPassword());
        } else if (Objects.equals(changePasswordModel.getUserType(), "teacher")) {
            updateTeacherPassword(changePasswordModel.getId(), changePasswordModel.getNewPassword());
        }
    }
}
