package com.ProjectHub.repository;

import com.ProjectHub.entities.StudentProfile;
import com.ProjectHub.model.StudentProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Avinash Vijayvargiya on 30-09-2021.
 */
@Repository
public interface UserRepository extends JpaRepository<StudentProfile, Integer> {
    Optional<StudentProfile> findByUsername(String username);

    @Query("select s.firstName, s.lastName from StudentProfile s where s.username=?1")
    StudentProfileModel getStudentProfileByUsername(String username);

//    @Query("select c from customer c where c.id=?1")
//    Optional<StudentProfileModel> getStudenByCustomerId(Integer customerId);
}
