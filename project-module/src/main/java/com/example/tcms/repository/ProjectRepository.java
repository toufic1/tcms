package com.example.tcms.repository;

import com.example.tcms.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository
        extends JpaRepository<Project,String> {
    boolean existsByName(String name);

    List<Project> findByArchivedFalse();

    Optional<Project> findByIdAndArchivedFalse(String id);

    List<Project> findByCreatedBy(String userId);
}