package com.ProjectHub.controller;

import com.ProjectHub.CSVHelper;
import com.ProjectHub.entities.StudentProfile;
import com.ProjectHub.entities.TeacherProfile;
import com.ProjectHub.model.ResponseMessage;
import com.ProjectHub.service.CSVService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Objects;

import static com.ProjectHub.util.Constants.BEARER_AUTH;

/**
 * Created by Avinash Vijayvargiya on 10-10-2021.
 */
@RestController
@RequestMapping("/api")
@SecurityRequirement(name = BEARER_AUTH)
public class AdminController {

    @Autowired
    CSVService fileService;

    @RequestMapping(value = "/uploadStudent", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseMessage> uploadStudentFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (CSVHelper.hasCSVFormat(file)) {
            try {
                fileService.saveStudent(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/csv/download/").path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message, fileDownloadUri));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message, ""));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message, ""));
    }

    @RequestMapping(value = "/uploadTeacher", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseMessage> uploadTeacherFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (CSVHelper.hasCSVFormat(file)) {
            try {
                fileService.saveTeacher(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/csv/download/").path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message, fileDownloadUri));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message, ""));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message, ""));
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentProfile>> getAllStudent() {
        try {
            List<StudentProfile> studentProfiles = fileService.getAllStudents();
            if (studentProfiles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(studentProfiles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/teacher")
    public ResponseEntity<List<TeacherProfile>> getAllTeacher() {
        try {
            List<TeacherProfile> teacherProfiles = fileService.getAllTeacher();
            if (teacherProfiles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(teacherProfiles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("student/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadStudentFile(@PathVariable String fileName) {
        InputStreamResource file = new InputStreamResource(fileService.loadStudent());

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName).contentType(MediaType.parseMediaType("application/csv")).body(file);
    }

    @GetMapping("teacher/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadTeacherFile(@PathVariable String fileName) {
        InputStreamResource file = new InputStreamResource(fileService.loadTeacher());

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName).contentType(MediaType.parseMediaType("application/csv")).body(file);
    }
}