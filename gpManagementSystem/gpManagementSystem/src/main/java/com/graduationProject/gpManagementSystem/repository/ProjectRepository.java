package com.graduationProject.gpManagementSystem.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graduationProject.gpManagementSystem.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByTeamId(Long teamId);

  
}

