package com.ProjectHub.controller;

import com.ProjectHub.documents.AdminProfile;
import com.ProjectHub.repository.AdminRepository;
import com.ProjectHub.repository.TeacherRepository;
import com.ProjectHub.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * User: Avinash Vijayvargiya
 * Date: 21-Feb-22
 * Time: 11:14 PM
 */
@RestController
@RequestMapping("/auth")
public class DebuggerController {

    @Resource
    private UserRepository userRepository;

    @Resource
    private TeacherRepository teacherRepository;

    @Resource
    private AdminRepository adminRepository;

    @GetMapping("/deleteAllStudent")
    public String deleteAllStudent() {
        userRepository.deleteAll();
        return null;
    }

    @GetMapping("/deleteAllTeacher")
    public String deleteAllTeacher() {
        teacherRepository.deleteAll();
        return null;
    }

    @GetMapping("/addAAdmin")
    public String addAdmin(AdminProfile adminProfile) {
        adminRepository.save(adminProfile);
        return "Admin Added";
    }
}
