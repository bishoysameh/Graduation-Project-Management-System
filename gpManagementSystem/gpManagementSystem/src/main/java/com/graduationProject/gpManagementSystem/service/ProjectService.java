package com.graduationProject.gpManagementSystem.service;

import java.util.List;
import java.util.Optional;


// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graduationProject.gpManagementSystem.exception.InvalidActionException;
import com.graduationProject.gpManagementSystem.exception.ResourceNotFoundException;
import com.graduationProject.gpManagementSystem.model.Project;
import com.graduationProject.gpManagementSystem.repository.ProjectRepository;

@Service
public class ProjectService
 {

 private final ProjectRepository projectRepository;

 public ProjectService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
}

    public List<Project> getAllProjects() {
        return projectRepository.findAll();  
    }





    public Optional<Project> getProjectById(Long id){
           return projectRepository.findById(id);
    }





    // public void addProject (Project project){
    //      projectRepository.save(project);
    // }
    public Project addProject(Project project) {
        if (project.getStartDate().isAfter(project.getEndDate())) {
            throw new InvalidActionException("Start date cannot be after end date");
        }
    
        Project savedProject = projectRepository.save(project);
    
       return savedProject;
    }
    




     // Update an existing doctor
     public Project updateProject(Long id, Project projectDetails) {

    if (projectDetails.getStartDate().isAfter(projectDetails.getEndDate())) {
        throw new InvalidActionException("Start date cannot be after end date");
    }

        Project updatedProject = projectRepository.findById(id).map(project -> {
            project.setDescription(projectDetails.getDescription());
            project.setEndDate(projectDetails.getEndDate());
            project.setStartDate(projectDetails.getStartDate());
            project.setProjectStatus(projectDetails.getProjectStatus());
            return projectRepository.save(project);
        }).orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        return updatedProject;
    }







    
    // public void deleteProject(Long id ){
    //      projectRepository.deleteById(id);
    // }

public void deleteProject(Long id) {
             projectRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
    projectRepository.deleteById(id);
}


 }
