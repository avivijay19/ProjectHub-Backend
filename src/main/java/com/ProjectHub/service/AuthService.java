package com.ProjectHub.service;

import com.ProjectHub.entities.StudentProfile;
import com.ProjectHub.entities.TeacherProfile;
import com.ProjectHub.model.ChangePasswordModel;
import com.ProjectHub.repository.TeacherRepository;
import com.ProjectHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    JavaMailSender javaMailSender;

    private boolean updateStudentPassword(String studentId, String newPassword, String oldPassword) {
        StudentProfile studentProfile = userRepository.findByUsername(studentId).get();
        if (Objects.equals(studentProfile.getPassword(), oldPassword)) {
            studentProfile.setPassword(newPassword);
            userRepository.save(studentProfile);
            sendEmail(studentProfile.getEmailId(), studentProfile.getFirstName(), studentProfile.getLastName());
            return true;
        }
        return false;
    }

    private boolean updateTeacherPassword(String teacherId, String newPassword, String oldPassword) {
        TeacherProfile teacherProfile = teacherRepository.findById(teacherId).get();
        if (Objects.equals(teacherProfile.getPassword(), oldPassword)) {
            teacherProfile.setPassword(newPassword);
            teacherRepository.save(teacherProfile);
            return true;
        }
        return false;
    }

    public boolean changePassword(ChangePasswordModel model) {
        if (Objects.equals(model.getUserType(), "student")) {
            return updateStudentPassword(model.getId(), model.getNewPassword(), model.getOldPassword());
        } else if (Objects.equals(model.getUserType(), "teacher")) {
            return updateTeacherPassword(model.getId(), model.getNewPassword(), model.getOldPassword());
        }
        return false;
    }

    String sendEmail(String emailId, String firstName, String lastName) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailId);
        msg.setSubject("Successfully Changed Password for ProjectHub.!");
        msg.setText("Hello " + firstName + " " + lastName + ", \nYou Have successfully changed the password..! \nTeam ProjectHub");
        javaMailSender.send(msg);
        return "Mail Sent";
    }
}
