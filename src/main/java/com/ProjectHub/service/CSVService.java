package com.ProjectHub.service;

import com.ProjectHub.CSVHelper;
import com.ProjectHub.entities.StudentProfile;
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

    public void save(MultipartFile file) {
        try {
            List<StudentProfile> tutorials = CSVHelper.csvToTutorials(file.getInputStream());
            repository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    public ByteArrayInputStream load() {
        List<StudentProfile> tutorials = repository.findAll();

        return CSVHelper.tutorialsToCSV(tutorials);
    }

    public List<StudentProfile> getAllTutorials() {
        return repository.findAll();
    }
}