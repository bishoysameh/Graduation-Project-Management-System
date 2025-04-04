package com.graduationProject.gpManagementSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graduationProject.gpManagementSystem.dto.ApiResponse;
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
    public ResponseEntity<ApiResponse<Team>> createTeam(@PathVariable String studentId ,@RequestBody TeamRequest request) {
       Team createdTeam =  teamService.createTeam(studentId, request);

          ApiResponse<Team> response = new ApiResponse<>(
            "success" ,
            "Team created successfully",
            createdTeam
         );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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




      @GetMapping("/doctor/{doctorId}")
public List<Team> getTeamsByDoctorId(@PathVariable Long doctorId) {


    return teamService.getTeamsByDoctorId(doctorId);
    // List<Team> teams = teamService.getTeamsByDoctorId(doctorId);
    
    // ApiResponse<List<Team>> response = new ApiResponse<>(
    //     "success",
    //     "Teams fetched successfully",
    //     teams
    // );
    
    // return ResponseEntity.ok(response);
    
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
    public ResponseEntity<ApiResponse<Team>> updateTeam(@PathVariable Long id, @RequestBody Team team) {        
        Team updatedTeam =  teamService.updateTeam(id,team);
        ApiResponse<Team> response = new ApiResponse<>(
            "success", 
            "Team updated successfully",
            updatedTeam
          );
    return ResponseEntity.status(HttpStatus.OK).body(response);
        }



    //work
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTeam(@PathVariable Long id ){
          teamService.deleteTeam(id);
         // Create success response
        ApiResponse<Void> successResponse = new ApiResponse<>(
            "success",
            "Team deleted successfully"
        );
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }
    


    
  
}
