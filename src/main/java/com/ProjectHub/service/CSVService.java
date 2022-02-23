package com.ProjectHub.service;

import com.ProjectHub.CSVHelper;
import com.ProjectHub.documents.StudentProfile;
import com.ProjectHub.documents.TeacherProfile;
import com.ProjectHub.repository.TeacherRepository;
import com.ProjectHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class CSVService {
    @Autowired
    UserRepository repository;

    @Autowired
    TeacherRepository teacherRepository;

    public void saveStudent(MultipartFile file) {
        try {
            List<StudentProfile> studentProfiles = CSVHelper.CSVToStudent(file.getInputStream());
            repository.saveAll(studentProfiles);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public void saveTeacher(MultipartFile file) {
        try {
            List<TeacherProfile> teacherProfiles = CSVHelper.CSVToTeacher(file.getInputStream());
            teacherRepository.saveAll(teacherProfiles);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream loadStudent() {
        List<StudentProfile> studentProfiles = repository.findAll();
        return CSVHelper.studentToCSV(studentProfiles);
    }

    public ByteArrayInputStream loadTeacher() {
        List<TeacherProfile> teacherProfiles = teacherRepository.findAll();
        return CSVHelper.teacherToCSV(teacherProfiles);
    }

    public List<StudentProfile> getAllStudents() {
        return repository.findAll();
    }

    public List<TeacherProfile> getAllTeacher() {
        return teacherRepository.findAll();
    }
}