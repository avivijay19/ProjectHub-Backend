package com.ProjectHub.repository;

import com.ProjectHub.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Created by Avinash Vijayvargiya on 22-09-2021.
 */

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> getProjectByProjectId(Long id);

    Optional<Project> findByProjectId (Long id);
}
