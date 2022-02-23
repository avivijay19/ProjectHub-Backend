package com.ProjectHub.repository;

import com.ProjectHub.documents.TeacherProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TeacherRepository extends MongoRepository<TeacherProfile, String> {
    Optional<TeacherProfile> findByEmployeeID(String username);
}
