package com.graduationProject.gpManagementSystem.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.graduationProject.gpManagementSystem.dto.ProposalRequest;
import com.graduationProject.gpManagementSystem.enums.ProjectStatus;
import com.graduationProject.gpManagementSystem.exception.InvalidActionException;
import com.graduationProject.gpManagementSystem.exception.ResourceNotFoundException;
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

import org.springframework.http.MediaType;

import com.graduationProject.gpManagementSystem.service.ProposalService;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

import java.nio.file.Path;




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



    
    private static final String UPLOAD_DIR = "uploads/proposals";



        public Optional<Proposal> getOneProposalById(Long id){
           return proposalRepository.findById(id);
    }




    //submit proposal
     public void submitProposal(ProposalRequest request , MultipartFile file) {
        // Find the student submitting the proposal
        Student student = studentRepository.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        // Ensure the student is part of a team
        if (student.getTeam() == null) {
            throw new InvalidActionException("Student must be in a team to submit a proposal");
        }

        // 🔹 Get the team from the student
        Team team = student.getTeam();

        // 🔹 Fetch doctors based on provided doctor IDs
        List<Doctor> doctors = doctorRepository.findAllById(request.getDoctorIds());
        if (doctors.isEmpty()) {
            throw new ResourceNotFoundException("No valid doctors found for the proposal");
        }



        validateFile(file);
        String filePath = saveFile(file);


        // Create a new proposal
        Proposal proposal = new Proposal();
        proposal.setTitle(request.getTitle());
        proposal.setDescription(request.getDescription());     
        proposal.setApproved(false);  // Default: Not yet approved
        proposal.setTeam(team);
        proposal.setSubmittedBy(student);
        proposal.setDoctors(doctors);
        proposal.setPdfPath(filePath);
        proposal.setFilename(file.getOriginalFilename());
        
        // Save the proposal
        proposalRepository.save(proposal);

    }











    public ResponseEntity<Resource> getPdf(String filename) throws IOException {
        Path filePath = Paths.get(UPLOAD_DIR).resolve(filename).toAbsolutePath();

        if (!Files.exists(filePath)) {
            throw new ResourceNotFoundException("File not found: " + filePath);
        }

        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists() || resource.isReadable()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                    .body(resource);
        } else {
            throw new RuntimeException("File not readable");
        }
    }






    private void validateFile(MultipartFile file) {
        String contentType = file.getContentType();
        long maxSize = 5 * 1024 * 1024; // 5MB

        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        if (file.getSize() > maxSize) {
            throw new RuntimeException("File exceeds the max allowed size");
        }

        if (!Objects.equals(contentType, "application/pdf")) {
            throw new RuntimeException("Only PDF files are allowed");
        }
    }





     private String saveFile(MultipartFile file) {
        // String uploadDir = "uploads/proposals";
          final String uploadDir = System.getProperty("user.dir") + "/uploads/proposals/";

        String filename = file.getOriginalFilename();
        String filePath = uploadDir + filename;

        try {
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            file.transferTo(new File(filePath));
            return filePath;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file", e);
        }
    }



    // Approve Proposal → Moves Proposal to Projects
    public void approveProposal(Long doctorId, Long proposalId) {

        Doctor doctor = getDoctorById(doctorId);
        Proposal proposal = getProposalById(proposalId);

        assignDoctorToTeam(proposal.getTeam(), doctor);
        moveProposalToProject(proposal);
        removePendingProposal(proposal);

    }






    // Reject Proposal → Removes it from the Doctor's Pending List
    public void rejectProposal(Long doctorId, Long proposalId) {

        Doctor doctor = getDoctorById(doctorId);
        Proposal proposal = getProposalById(proposalId);
        
        // Remove doctor from the proposal's doctor list
        proposal.getDoctors().remove(doctor);
        proposalRepository.save(proposal);
        
        // If no more doctors are reviewing, mark as rejected
        if (proposal.getDoctors().isEmpty()) {
            proposal.setApproved(false);
            proposalRepository.save(proposal);
        }
    }







    // Get Pending Proposals for a Doctor
    public List<Proposal> getPendingProposals(Long doctorId) {
        Doctor doctor = getDoctorById(doctorId);
        return proposalRepository.findByDoctorsContaining(doctor);
    }





    // utility functions 

    //  Assign Doctor to Team
    private void assignDoctorToTeam(Team team, Doctor doctor) {
        team.setDoctor(doctor);
        teamRepository.save(team);
    }


    // Move Proposal to Project Table
    private void moveProposalToProject(Proposal proposal) {
        Project project = new Project();
        project.setTitle(proposal.getTitle());
        project.setDescription(proposal.getDescription());
        project.setTeam(proposal.getTeam());
        project.setProjectStatus(ProjectStatus.TODO);
        projectRepository.save(project);
    }

    //  Remove Proposal from Pending List
    private void removePendingProposal(Proposal proposal) {
        proposalRepository.delete(proposal);
    }

    //  Get Proposal by ID
    // private Proposal getProposalById(Long proposalId) {
    //     return proposalRepository.findById(proposalId)
    //             .orElseThrow(() -> new RuntimeException("Proposal not found"));
    // }


    private Proposal getProposalById(Long proposalId) {
    return proposalRepository.findById(proposalId)
            .orElseThrow(() -> new ResourceNotFoundException("Proposal with ID " + proposalId + " not found"));
}

    //   Get Doctor by ID
    private Doctor getDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
    }
}
