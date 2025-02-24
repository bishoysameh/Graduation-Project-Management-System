package com.graduationProject.gpManagementSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graduationProject.gpManagementSystem.dto.ProposalRequest;
import com.graduationProject.gpManagementSystem.enums.ProjectStatus;
import com.graduationProject.gpManagementSystem.model.Doctor;
import com.graduationProject.gpManagementSystem.model.Project;
import com.graduationProject.gpManagementSystem.model.Proposal;
import com.graduationProject.gpManagementSystem.model.Student;
import com.graduationProject.gpManagementSystem.model.Team;
import com.graduationProject.gpManagementSystem.repository.DoctorRepository;
import com.graduationProject.gpManagementSystem.repository.ProjectRepository;
import com.graduationProject.gpManagementSystem.repository.ProposalRepository;
import com.graduationProject.gpManagementSystem.repository.StudentRepository;
import com.graduationProject.gpManagementSystem.repository.TeamRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProposalService {

    @Autowired
    private ProposalRepository proposalRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private StudentRepository studentRepository;


    //submit proposal
public Proposal submitProposal(ProposalRequest request) {
        // 🔹 Find the student submitting the proposal
        Student student = studentRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // 🔹 Ensure the student is part of a team
        if (student.getTeam() == null) {
            throw new RuntimeException("Student must be in a team to submit a proposal");
        }

        // 🔹 Get the team from the student
        Team team = student.getTeam();

        // 🔹 Fetch doctors based on provided doctor IDs
        List<Doctor> doctors = doctorRepository.findAllById(request.getDoctorIds());
        if (doctors.isEmpty()) {
            throw new RuntimeException("No valid doctors found for the proposal");
        }

        // 🔹 Create a new proposal
        Proposal proposal = new Proposal();
        proposal.setTitle(request.getTitle());
        proposal.setDescription(request.getDescription());
        proposal.setStartDate(request.getStartDate());
        proposal.setEndDate(request.getEndDate());
        proposal.setApproved(false);  // Default: Not yet approved
        proposal.setTeam(team);
        proposal.setSubmittedBy(student);
        proposal.setDoctors(doctors);
        
        // 🔹 Save the proposal
        return proposalRepository.save(proposal);
    }







    // ✅ Approve Proposal → Moves Proposal to Projects
    public void approveProposal(Long proposalId, Long doctorId) {
        Proposal proposal = getProposalById(proposalId);
        Doctor doctor = getDoctorById(doctorId);

        assignDoctorToTeam(proposal.getTeam(), doctor);
        moveProposalToProject(proposal);
        removePendingProposal(proposal);
    }



    // ✅ Reject Proposal → Removes it from the Doctor's Pending List
    public void rejectProposal(Long proposalId, Long doctorId) {
        Proposal proposal = getProposalById(proposalId);
        Doctor doctor = getDoctorById(doctorId);
        
        // Remove doctor from the proposal's doctor list
        proposal.getDoctors().remove(doctor);
        proposalRepository.save(proposal);
        
        // If no more doctors are reviewing, mark as rejected
        if (proposal.getDoctors().isEmpty()) {
            proposal.setApproved(false);
            proposalRepository.save(proposal);
        }
    }



    // ✅ Get Pending Proposals for a Doctor
    public List<Proposal> getPendingProposals(Long doctorId) {
        Doctor doctor = getDoctorById(doctorId);
        return proposalRepository.findByDoctorsContaining(doctor);
    }





    // utility functions 

    // ✅ Assign Doctor to Team
    private void assignDoctorToTeam(Team team, Doctor doctor) {
        team.setDoctor(doctor);
        teamRepository.save(team);
    }


    // ✅ Move Proposal to Project Table
    private void moveProposalToProject(Proposal proposal) {
        Project project = new Project();
        project.setTitle(proposal.getTitle());
        project.setDescription(proposal.getDescription());
        project.setStartDate(proposal.getStartDate());
        project.setEndDate(proposal.getEndDate());
        project.setTeam(proposal.getTeam());
        project.setProjectStatus(ProjectStatus.TODO);
        projectRepository.save(project);
    }

    // ✅ Remove Proposal from Pending List
    private void removePendingProposal(Proposal proposal) {
        proposalRepository.delete(proposal);
    }

    // ✅ Get Proposal by ID
    // private Proposal getProposalById(Long proposalId) {
    //     return proposalRepository.findById(proposalId)
    //             .orElseThrow(() -> new RuntimeException("Proposal not found"));
    // }


    private Proposal getProposalById(Long proposalId) {
    return proposalRepository.findById(proposalId)
            .orElseThrow(() -> new EntityNotFoundException("Proposal with ID " + proposalId + " not found"));
}

    // ✅ Get Doctor by ID
    private Doctor getDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }
}
