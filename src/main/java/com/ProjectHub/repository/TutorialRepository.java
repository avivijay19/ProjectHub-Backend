package com.ProjectHub.repository;

import com.ProjectHub.entities.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Avinash Vijayvargiya on 22-09-2021.
 */

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    List<Tutorial> findByTitleContaining(String title);
}
