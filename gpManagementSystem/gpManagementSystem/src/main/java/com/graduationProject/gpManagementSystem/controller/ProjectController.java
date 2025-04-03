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
import com.graduationProject.gpManagementSystem.model.Project;
import com.graduationProject.gpManagementSystem.service.ProjectService;

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

    @GetMapping
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }



    
    @GetMapping("/{id}")
    public Optional<Project> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }





    @PostMapping
    public ResponseEntity<ApiResponse<Project>> addProject(@RequestBody Project project) {
        Project savedProject =  projectService.addProject(project);
         ApiResponse<Project> response = new ApiResponse<>(
            "success", 
            "Project added successfully", 
            savedProject
        );
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }





    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Project>> updateProject(@PathVariable Long id, @RequestBody Project project) {        
        Project updatedProject =  projectService.updateProject(id,project);
        ApiResponse<Project> response = new ApiResponse<>(
            "success", 
            "Project updated successfully", 
            updatedProject
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }




    //work
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProject(@PathVariable Long id ){
          projectService.deleteProject(id);
          ApiResponse<Void> successResponse = new ApiResponse<>(
            "success",
            "Project deleted successfully"
        );
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }
    


  
}
