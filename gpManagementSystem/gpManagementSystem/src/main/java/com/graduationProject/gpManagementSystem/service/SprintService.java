package com.graduationProject.gpManagementSystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.graduationProject.gpManagementSystem.dto.ApiResponse;
import com.graduationProject.gpManagementSystem.dto.SprintRequest;
import com.graduationProject.gpManagementSystem.enums.SprintStatus;
import com.graduationProject.gpManagementSystem.exception.ResourceNotFoundException;
import com.graduationProject.gpManagementSystem.model.Project;
import com.graduationProject.gpManagementSystem.model.Sprint;
import com.graduationProject.gpManagementSystem.model.Task;
import com.graduationProject.gpManagementSystem.repository.ProjectRepository;
import com.graduationProject.gpManagementSystem.repository.SprintRepository;
import com.graduationProject.gpManagementSystem.repository.TaskRepository;

@Service
public class SprintService {

    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;






    public Sprint createSprint(SprintRequest request) {
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        List<Task> selectedTasks = taskRepository.findAllById(request.getTaskIds());

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusWeeks(request.getDurationWeeks());

        Sprint sprint = new Sprint(request.getName(), request.getDescription(), project, startTime, endTime, SprintStatus.ACTIVE, selectedTasks);

        sprint = sprintRepository.save(sprint);

        for (Task task : selectedTasks) {
            task.setSprint(sprint);
        }
        taskRepository.saveAll(selectedTasks); 

   return sprint; 
  }






    // ✅ Get All Sprints for a Project
    public List<Sprint> getSprintsByProject(Long projectId) {
        return sprintRepository.findByProjectId(projectId);
    }






    
    
    // ✅ End Sprint Automatically (Scheduler Calls This)
    public ResponseEntity<ApiResponse<Sprint>> endSprint(Long sprintId) {
        Sprint sprint = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new ResourceNotFoundException("Sprint not found"));

        sprint.setStatus(SprintStatus.ENDED);
        sprintRepository.save(sprint);

    ApiResponse<Sprint> response = new ApiResponse<>(
        "success",
        "Sprint ended successfully",
        sprint
    );

    return ResponseEntity.ok(response);
    }



    // ✅ End Sprint Automatically (Scheduler Calls This)
    // public void endSprint(Long sprintId) {
    //     Sprint sprint = sprintRepository.findById(sprintId)
    //             .orElseThrow(() -> new RuntimeException("Sprint not found"));

    //     sprint.setStatus(SprintStatus.ENDED);
    //     sprintRepository.save(sprint);
    // }
}
