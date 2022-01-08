package com.ProjectHub.repository;

import com.ProjectHub.entities.AdminProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminProfile, String> {
    Optional<AdminProfile> findByUsernameAdmin(String username);
}
