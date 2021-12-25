package com.ProjectHub.repository;

import com.ProjectHub.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Avinash Vijayvargiya on 22-09-2021.
 */

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> getProjectByProjectId(Long id);

    Project findByProjectId(Long id);

    @Query(value = "SELECT * FROM project p WHERE p.guideid =:teacherId AND p.closed =:status ORDER BY p.project_id", nativeQuery = true)
    List<Project> findAllByTeacherId(@Param("teacherId") Long teacherId, @Param("status") String status);
//    @Modifying
//    @Query("update Project p set p.approvedByTeacher = ?1 where p.projectId = ?2")
//    void approvedByTeacher(String PROJECT_APPROVED,@Param("id") long id);
}
