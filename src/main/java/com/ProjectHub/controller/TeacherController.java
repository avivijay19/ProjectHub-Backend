package com.ProjectHub.controller;

import com.ProjectHub.entities.Project;
import com.ProjectHub.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Avinash Vijayvargiya on 10-10-2021.
 */
@RestController
@RequestMapping("/api")
public class TeacherController {

    @Autowired
    ProjectRepository projectRepository;

    /**
     * the endpoint is used get all the projects in list view and detailed project view with project id
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

    /**
     * fetch all the details of the ongoing project for the teacher.
     * @param id
     * @return
     */
    @GetMapping("/projects/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") long id) {
        Optional<Project> projectById = projectRepository.findByProjectId(id);
        return projectById.map(project -> new ResponseEntity<>(project, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
