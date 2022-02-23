package com.ProjectHub.repository;

import com.ProjectHub.documents.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Avinash Vijayvargiya on 22-09-2021.
 */

public interface ProjectRepository extends MongoRepository<Project, Long> {
    List<Project> getProjectByProjectId(Long id);

    Project findByProjectId(Long id);


    //    @Query(value = "SELECT * FROM project p WHERE p.guideid =:teacherId AND p.closed =:status ORDER BY p.project_id", nativeQuery = true)
    @Query("db.project p.find({$and:[{p: :teacherId },{ p: :status }]}).sort({ p.project_id:})")
    List<Project> findAllByTeacherId(@Param("teacherId") Long teacherId, @Param("status") String status);
//    @Modifying
//    @Query("update Project p set p.approvedByTeacher = ?1 where p.projectId = ?2")
//    void approvedByTeacher(String PROJECT_APPROVED,@Param("id") long id);
}
