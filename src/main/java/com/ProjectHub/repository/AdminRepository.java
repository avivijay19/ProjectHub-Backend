package com.ProjectHub.repository;

import com.ProjectHub.entities.AdminProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdminRepository extends MongoRepository<AdminProfile, String> {
    Optional<AdminProfile> findByUsernameAdmin(String username);
}
