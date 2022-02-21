package com.ProjectHub.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("admin")
public class AdminProfile {
    @Id
    private String usernameAdmin;

    private String passwordAdmin;
    private String firstNameAdmin;
    private String lastNameAdmin;
    private String departmentAdmin;
    private String emailIdAdmin;
    private String rolesAdmin;

    public boolean isActive() {
        return true;
    }
}
