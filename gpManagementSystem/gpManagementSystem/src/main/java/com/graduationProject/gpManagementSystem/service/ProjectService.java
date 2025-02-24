package com.graduationProject.gpManagementSystem.service;

import java.util.List;
import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graduationProject.gpManagementSystem.model.Doctor;
import com.graduationProject.gpManagementSystem.model.Project;
import com.graduationProject.gpManagementSystem.repository.DoctorRepository;
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

    public void addProject (Project project){
         projectRepository.save(project);
    }

     // Update an existing doctor
     public Project updateProject(Long id, Project projectDetails) {
        return projectRepository.findById(id).map(project -> {
            project.setDescription(projectDetails.getDescription());
            project.setEndDate(projectDetails.getEndDate());
            project.setStartDate(projectDetails.getStartDate());
            project.setProjectStatus(projectDetails.getProjectStatus());
            return projectRepository.save(project);
        }).orElseThrow(() -> new RuntimeException("Project not found"));
    }



    public void deleteProject(Long id ){
         projectRepository.deleteById(id);
    }


 }
