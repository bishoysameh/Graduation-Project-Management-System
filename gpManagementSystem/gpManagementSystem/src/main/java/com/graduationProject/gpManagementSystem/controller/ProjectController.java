package com.graduationProject.gpManagementSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graduationProject.gpManagementSystem.model.Doctor;
import com.graduationProject.gpManagementSystem.model.Project;
import com.graduationProject.gpManagementSystem.model.Team;
import com.graduationProject.gpManagementSystem.service.DoctorService;
import com.graduationProject.gpManagementSystem.service.ProjectService;
import com.graduationProject.gpManagementSystem.service.TeamService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController
 {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // API to fetch all doctors
    //work
    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }
//work
    @GetMapping("/{id}")
    public Optional<Project> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    // @PostMapping
    // public ResponseEntity<?> addDoctor(@RequestBody Doctor doctor) {
    //     return ResponseEntity.ok(doctorService.addDoctor(doctor));
    // }

    @PostMapping
    public void addProject(@RequestBody Project project) {
         projectService.addProject(project);
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project project) {        
        return projectService.updateProject(id,project);
    }

    //work
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id ){
         projectService.deleteProject(id);
    }
    


    
  
}
