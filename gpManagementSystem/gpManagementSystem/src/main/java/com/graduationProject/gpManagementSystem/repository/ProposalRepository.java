package com.graduationProject.gpManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graduationProject.gpManagementSystem.model.Doctor;
import com.graduationProject.gpManagementSystem.model.Proposal;

public interface ProposalRepository extends JpaRepository<Proposal,Long> {
// Get all proposals assigned to a specific doctor (Pending proposals for a doctor)
    List<Proposal> findByDoctorsContaining(Doctor doctor);    
}  

