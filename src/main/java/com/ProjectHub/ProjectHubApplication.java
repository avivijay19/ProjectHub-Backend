package com.ProjectHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class} )
public class ProjectHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectHubApplication.class, args);
	}

}
