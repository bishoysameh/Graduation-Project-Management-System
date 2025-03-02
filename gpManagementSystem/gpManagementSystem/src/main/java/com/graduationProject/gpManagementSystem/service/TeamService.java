package com.graduationProject.gpManagementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.graduationProject.gpManagementSystem.dto.TeamRequest;
import com.graduationProject.gpManagementSystem.exception.CustomException;
import com.graduationProject.gpManagementSystem.model.Student;
import com.graduationProject.gpManagementSystem.model.Team;
import com.graduationProject.gpManagementSystem.repository.TeamRepository;

@Service
public class TeamService {

private final StudentService studentService;
 private final TeamRepository teamRepository;

 public TeamService(TeamRepository teamRepository , StudentService studentService) {
    this.teamRepository = teamRepository;
    this.studentService = studentService;
}




    public List<Team> getAllTeams() {
        return teamRepository.findAll();  
    }

    public Optional<Team> getTeamById(Long id){
           return teamRepository.findById(id);
    }

    public void addTeam (Team team){
         teamRepository.save(team);
    }

     // Update an existing doctor
     public Team updateTeam(Long id, Team teamDetails) {
        return teamRepository.findById(id).map(team -> {
           team.setName(teamDetails.getName());
           return teamRepository.save(team);
        }).orElseThrow(() -> new RuntimeException("Team not found"));
    }

    public void deleteTeam(Long id ){
         teamRepository.deleteById(id);
    }






    public ResponseEntity<?> createTeam(String studentId , TeamRequest request) {
          // Get the requesting student
        Optional<Student> requesterOpt = studentService.findByStudentId(studentId);

        if (requesterOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Requesting student not found.");
        }

        Student requester = requesterOpt.get();

        // Ensure the requester is not already in a team
        if (requester.getTeam() != null) {
            throw new CustomException("STUDENT_ALREADY_IN_TEAM", "You are already in a team and cannot create another one.");
        }

        // Ensure exactly 6 student IDs are provided
        if (request.getStudentIds().size() != 6) {
            return ResponseEntity.badRequest().body("A team must have exactly 6 members.");
        }   

         // Fetch students by studentId
         List<Student> students = studentService.findByStudentIds(request.getStudentIds());

            // Validate all students exist
        if (students.size() != 6) {
            return ResponseEntity.badRequest().body("Some student IDs are invalid.");
        }


        if (students.stream().anyMatch(student-> student.getTeam() != null)){
            return ResponseEntity.badRequest().body("One or more students are already in a team.");
        }



        // Create and save the team
        Team newTeam = new Team();
        newTeam.setName(request.getName());
        newTeam.setStudents(students);

        teamRepository.save(newTeam);


        // Assign the team to each student
        for (Student student : students) {
            student.setTeam(newTeam);
            studentService.saveStudent(student);
        }

         teamRepository.save(newTeam);

         return ResponseEntity.ok().body("Team created successfully");
    }

 }
