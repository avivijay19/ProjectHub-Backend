package com.ProjectHub.repository;

import com.ProjectHub.entities.TeacherProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<TeacherProfile, String> {
    Optional<TeacherProfile> findByEmployeeID(String username);
}
