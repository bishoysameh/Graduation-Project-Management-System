package com.graduationProject.gpManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.graduationProject.gpManagementSystem.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByDoctorId(Long doctorId);

}

