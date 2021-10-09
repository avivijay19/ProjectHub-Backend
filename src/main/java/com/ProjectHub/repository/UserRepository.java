package com.ProjectHub.repository;

import com.ProjectHub.entities.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Avinash Vijayvargiya on 30-09-2021.
 */
@Repository
public interface UserRepository extends JpaRepository<StudentProfile, Integer> {
    Optional<StudentProfile> findByUsername(String username);
}
