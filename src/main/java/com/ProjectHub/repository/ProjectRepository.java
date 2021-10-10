package com.ProjectHub.repository;

import com.ProjectHub.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import static com.ProjectHub.util.Constants.PROJECT_APPROVED;

import java.util.List;
import java.util.Optional;

/**
 * Created by Avinash Vijayvargiya on 22-09-2021.
 */

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> getProjectByProjectId(Long id);

    Optional<Project> findByProjectId (Long id);



    @Modifying
    @Query("update Project p set p.approvedByTeacher = ?1 where p.projectId = ?2")
    void approvedByTeacher(String PROJECT_APPROVED,@Param("id") long id);
}
