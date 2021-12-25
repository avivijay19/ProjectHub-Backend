package com.ProjectHub.repository;

import com.ProjectHub.entities.TeacherProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<TeacherProfile, String> {
}
