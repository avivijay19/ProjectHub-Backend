package com.ProjectHub.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("teacherdetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherProfile {
    @Id
    private String employeeID;

    private String password;
    private String firstName;
    private String lastName;
    private String department;
    private String emailId;
    private String roles;

    public boolean isActive() {
        return true;
    }
}