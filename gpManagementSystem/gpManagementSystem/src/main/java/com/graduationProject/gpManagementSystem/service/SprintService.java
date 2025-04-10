package com.graduationProject.gpManagementSystem.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.graduationProject.gpManagementSystem.dto.ApiResponse;
import com.graduationProject.gpManagementSystem.dto.SprintRequest;
import com.graduationProject.gpManagementSystem.enums.SprintStatus;
import com.graduationProject.gpManagementSystem.enums.TaskStatus;
import com.graduationProject.gpManagementSystem.exception.ResourceNotFoundException;
import com.graduationProject.gpManagementSystem.model.Project;
import com.graduationProject.gpManagementSystem.model.Sprint;
import com.graduationProject.gpManagementSystem.model.Task;
import com.graduationProject.gpManagementSystem.model.TaskHistory;
import com.graduationProject.gpManagementSystem.repository.ProjectRepository;
import com.graduationProject.gpManagementSystem.repository.SprintRepository;
import com.graduationProject.gpManagementSystem.repository.TaskHistoryRepository;
import com.graduationProject.gpManagementSystem.repository.TaskRepository;

@Service
public class SprintService {

    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskHistoryRepository taskHistoryRepository;






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

        List<Task> sprintTasks = taskRepository.findBySprint(sprint);

        for (Task task : sprintTasks) {
            if (task.getStudent() != null) {

                TaskHistory history = new TaskHistory();
                history.setOriginalTaskId(task.getId());
                history.setTitle(task.getTitle());
                history.setDescription(task.getDescription());
                history.setTaskStatus(task.getTaskStatus()); 
                history.setStartDate(task.getStartDate());
                history.setEndDate(task.getEndDate());
                history.setCompletedDate(
                    task.getTaskStatus() == TaskStatus.DONE ? LocalDate.now() : null
                );
                history.setStudent(task.getStudent());
                history.setProject(task.getProject());
                history.setSprint(task.getSprint());
    
                taskHistoryRepository.save(history);
            }
            
            if (task.getTaskStatus() != TaskStatus.DONE) {
                task.setSprint(null);
                task.setStudent(null);
            }
              taskRepository.save(task); 
    }

    ApiResponse<Sprint> response = new ApiResponse<>(
        "success",
        "Sprint ended successfully",
        sprint
    );

    return ResponseEntity.ok(response);
}










    // if (sprint.getStatus() == SprintStatus.ENDED) {
    //     List<Task> undoneTasks = taskRepository.findBySprintAndStatusNot(sprint, TaskStatus.DONE);
    //     for (Task task : undoneTasks) {
    //         task.setSprint(null);
    //         task.setStudent(null);
    //     }
    //     taskRepository.saveAll(undoneTasks);
    // }
///////////////////////////////////////////////////////////////////
/// 
///
///  



    // ✅ End Sprint Automatically (Scheduler Calls This)
    // public void endSprint(Long sprintId) {
    //     Sprint sprint = sprintRepository.findById(sprintId)
    //             .orElseThrow(() -> new RuntimeException("Sprint not found"));

    //     sprint.setStatus(SprintStatus.ENDED);
    //     sprintRepository.save(sprint);
    // }






public Map<LocalDate, Integer> getBurndownData(Long sprintId) {
    Sprint sprint = sprintRepository.findById(sprintId)
            .orElseThrow(() -> new ResourceNotFoundException("Sprint not found"));
    
    List<Task> tasks = taskRepository.findBySprintId(sprintId);
    LocalDate start = sprint.getStartTime().toLocalDate();
    LocalDate end = sprint.getEndTime().toLocalDate();
    
    Map<LocalDate, Integer> dailyRemaining = new LinkedHashMap<>();
    
    // Initialize with all tasks at start
    int remainingTasks = tasks.size();
    dailyRemaining.put(start.minusDays(1), remainingTasks); // Day before sprint starts
    
    // Group completed tasks by their completion date
    Map<LocalDate, Long> dailyCompletions = tasks.stream()
            .filter(task -> task.getTaskStatus() == TaskStatus.DONE)
            .filter(task -> task.getEndDate() != null)
            .collect(Collectors.groupingBy(
                Task::getEndDate,
                Collectors.counting()
            ));
    
    // Calculate remaining tasks for each day
    for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
        // Subtract tasks completed on this date
        remainingTasks -= dailyCompletions.getOrDefault(date, 0L).intValue();
        dailyRemaining.put(date, remainingTasks);
    }
    
    return dailyRemaining;
}




// public List<Sprint> getSprintsByProjectId(Long projectId) {
//     return sprintRepository.findByProjectId(projectId);
// }


}
