package com.ProjectHub.repository;

import com.ProjectHub.model.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Avinash Vijayvargiya on 22-09-2021.
 */

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    List<Tutorial> findByPublished(boolean published);

    List<Tutorial> findByTitleContaining(String title);
}
