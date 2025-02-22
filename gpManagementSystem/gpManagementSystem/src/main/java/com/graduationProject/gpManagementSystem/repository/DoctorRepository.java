package com.graduationProject.gpManagementSystem.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.graduationProject.gpManagementSystem.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByRole(String role);
}

