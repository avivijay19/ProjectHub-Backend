package com.ProjectHub.repository;

import com.ProjectHub.documents.StudentProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Avinash Vijayvargiya on 30-09-2021.
 */
@Repository
public interface UserRepository extends MongoRepository<StudentProfile, Integer> {
    Optional<StudentProfile> findByUsername(String username);
}
