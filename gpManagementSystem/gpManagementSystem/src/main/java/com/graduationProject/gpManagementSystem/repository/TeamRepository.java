package com.graduationProject.gpManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.graduationProject.gpManagementSystem.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}

