package com.graduationProject.gpManagementSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graduationProject.gpManagementSystem.dto.TeamRequest;
import com.graduationProject.gpManagementSystem.model.Doctor;
import com.graduationProject.gpManagementSystem.model.Student;
import com.graduationProject.gpManagementSystem.model.Team;
import com.graduationProject.gpManagementSystem.service.DoctorService;
import com.graduationProject.gpManagementSystem.service.StudentService;
import com.graduationProject.gpManagementSystem.service.TeamService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/v1/teams")
public class TeamController {
    

    private final TeamService teamService;
    private final StudentService studentService;


    public TeamController(TeamService teamService , StudentService studentService) {
        this.teamService = teamService;
        this.studentService = studentService;
    }



    @PostMapping("create/{studentId}")
    public ResponseEntity<?> createTeam(@PathVariable String studentId ,@RequestBody TeamRequest request) {

        // Get the requesting student
        Optional<Student> requesterOpt = studentService.findByStudentId(studentId);

        if (requesterOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Requesting student not found.");
        }

        Student requester = requesterOpt.get();

        // Ensure the requester is not already in a team
        if (requester.getTeam() != null) {
            return ResponseEntity.badRequest().body("You are already in a team and cannot create another one.");
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

        Team savedTeam = teamService.createTeam(newTeam);


        // Assign the team to each student
        for (Student student : students) {
            student.setTeam(savedTeam);
            studentService.saveStudent(student);
        }

        return ResponseEntity.ok(savedTeam);

    }
    





















    // API to fetch all doctors
    //work
    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }
//work
    @GetMapping("/{id}")
    public Optional<Team> getTeamById(@PathVariable Long id) {
        return teamService.getTeamById(id);
    }

    // @PostMapping
    // public ResponseEntity<?> addDoctor(@RequestBody Doctor doctor) {
    //     return ResponseEntity.ok(doctorService.addDoctor(doctor));
    // }

    @PostMapping
    public void addTeam(@RequestBody Team team) {
         teamService.addTeam(team);
    }

    @PutMapping("/{id}")
    public Team updateTeam(@PathVariable Long id, @RequestBody Team team) {        
        return teamService.updateTeam(id,team);
    }

    //work
    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Long id ){
         teamService.deleteTeam(id);
    }
    


    
  
}
