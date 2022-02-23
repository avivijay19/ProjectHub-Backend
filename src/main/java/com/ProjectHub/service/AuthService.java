package com.ProjectHub.service;

import com.ProjectHub.documents.AdminProfile;
import com.ProjectHub.documents.StudentProfile;
import com.ProjectHub.documents.TeacherProfile;
import com.ProjectHub.model.ChangePasswordModel;
import com.ProjectHub.model.security.JwtAuthenticationToken;
import com.ProjectHub.model.security.JwtUser;
import com.ProjectHub.repository.AdminRepository;
import com.ProjectHub.repository.TeacherRepository;
import com.ProjectHub.repository.UserRepository;
import com.ProjectHub.security.jwt.JwtGenerator;
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
    AdminRepository adminRepository;

    @Autowired
    JavaMailSender javaMailSender;


    @Autowired
    private JwtGenerator jwtGenerator;

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

    public JwtAuthenticationToken studentLogin(JwtUser jwtUser) {
        StudentProfile studentProfile = userRepository.findByUsername(jwtUser.getUsername()).get();
        if (Objects.equals(studentProfile.getPassword(), jwtUser.getPassword())) {
            jwtUser.setRole("ROLE_STUDENT");
            String token = jwtGenerator.generate(jwtUser);
            return new JwtAuthenticationToken(null, null, null,
                    token, studentProfile.getFirstName(), studentProfile.getLastName(), studentProfile.getUsername());
        }
        return null;
    }

    public JwtAuthenticationToken teacherLogin(JwtUser jwtUser) {
        TeacherProfile teacherProfile = teacherRepository.findByEmployeeID(jwtUser.getUsername()).get();
        if (Objects.equals(teacherProfile.getPassword(), jwtUser.getPassword())) {
            jwtUser.setRole("ROLE_TEACHER");
            String token = jwtGenerator.generate(jwtUser);
            return new JwtAuthenticationToken(null, null, null,
                    token, teacherProfile.getFirstName(), teacherProfile.getLastName(), teacherProfile.getEmployeeID());
        }
        return null;
    }

    public JwtAuthenticationToken adminLogin(JwtUser jwtUser) {
        AdminProfile adminProfile = adminRepository.findByUsernameAdmin(jwtUser.getUsername()).get();
        if (Objects.equals(adminProfile.getPasswordAdmin(), jwtUser.getPassword())) {
            jwtUser.setRole("ROLE_ADMIN");
            String token = jwtGenerator.generate(jwtUser);
            return new JwtAuthenticationToken(null, null, null,
                    token, adminProfile.getFirstNameAdmin(), adminProfile.getLastNameAdmin(), adminProfile.getUsernameAdmin());
        }
        return null;
    }
}
