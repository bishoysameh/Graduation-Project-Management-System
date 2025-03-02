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
import com.graduationProject.gpManagementSystem.model.Team;
import com.graduationProject.gpManagementSystem.service.StudentService;
import com.graduationProject.gpManagementSystem.service.TeamService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/v1/teams")
public class TeamController {
    

    private final TeamService teamService;


    public TeamController(TeamService teamService , StudentService studentService) {
        this.teamService = teamService;
    }



    @PostMapping("create/{studentId}")
    public ResponseEntity<?> createTeam(@PathVariable String studentId ,@RequestBody TeamRequest request) {
        return teamService.createTeam(studentId, request);
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
