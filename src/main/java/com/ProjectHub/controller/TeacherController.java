package com.ProjectHub.controller;

import com.ProjectHub.entities.Project;
import com.ProjectHub.model.ProjectDetails;
import com.ProjectHub.repository.ProjectRepository;
import com.ProjectHub.service.ProjectProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Avinash Vijayvargiya on 10-10-2021.
 */
@RestController
@RequestMapping("/api")
public class TeacherController {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectProfileService projectProfileService;

    /**
     * the endpoint is used get all the projects in list view and detailed project view with project id
     *
     * @param id
     * @return
     */
    @GetMapping("/teacherProject")
    public ResponseEntity<List<Project>> getAllProjects(@RequestParam(required = false) Long id) {
        try {
            List<Project> tutorials = new ArrayList<>();

            if (id == null)
                tutorials.addAll(projectRepository.findAll());
            else
                tutorials.addAll(projectRepository.getProjectByProjectId(id));

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/teacherProjectDetails/{projectID}")
    public ResponseEntity<ProjectDetails> getProjectDetails(@PathVariable Long projectID) {
        try {
            ProjectDetails project = projectProfileService.getProjectDetails(projectID);
            return new ResponseEntity<>(project, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
