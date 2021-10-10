package com.ProjectHub.controller;

import com.ProjectHub.entities.Project;
import com.ProjectHub.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
