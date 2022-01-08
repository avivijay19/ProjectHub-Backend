package com.ProjectHub.service;

import com.ProjectHub.entities.AdminProfile;
import com.ProjectHub.entities.StudentProfile;
import com.ProjectHub.entities.TeacherProfile;
import com.ProjectHub.model.MyUserDetails;
import com.ProjectHub.repository.AdminRepository;
import com.ProjectHub.repository.TeacherRepository;
import com.ProjectHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Created by Avinash Vijayvargiya on 30-09-2021.
 */
@Service
public class JPAUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<StudentProfile> user = userRepository.findByUsername(userName);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
        return user.map(MyUserDetails::new).get();
    }

    public UserDetails loadUserByEmployeeId(String userName) throws UsernameNotFoundException {
        Optional<TeacherProfile> user = teacherRepository.findByEmployeeID(userName);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
        return user.map(MyUserDetails::new).get();
    }

    public UserDetails loadUserByUsernameAdmin(String userName) throws UsernameNotFoundException {
        Optional<AdminProfile> user = adminRepository.findByUsernameAdmin(userName);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
        return user.map(MyUserDetails::new).get();
    }

}
