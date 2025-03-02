package com.graduationProject.gpManagementSystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graduationProject.gpManagementSystem.dto.SprintRequest;
import com.graduationProject.gpManagementSystem.enums.SprintStatus;
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

    // ✅ Create Sprint (Tasks are added from project tasks)
    public Sprint createSprint(SprintRequest request) {
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        List<Task> selectedTasks = taskRepository.findAllById(request.getTaskIds());

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusWeeks(request.getDurationWeeks());

        Sprint sprint = new Sprint(request.getName(), request.getDescription(), project, startTime, endTime, SprintStatus.ACTIVE, selectedTasks);

        // Save sprint first to generate an ID
        sprint = sprintRepository.save(sprint);

        // Assign selected tasks to the sprint
        for (Task task : selectedTasks) {
            task.setSprint(sprint);
        }
        taskRepository.saveAll(selectedTasks); // ✅ Save tasks with sprint_id

        return sprint;
    }

    // ✅ Get All Sprints for a Project
    public List<Sprint> getSprintsByProject(Long projectId) {
        return sprintRepository.findByProjectId(projectId);
    }

    // ✅ End Sprint Automatically (Scheduler Calls This)
    public void endSprint(Long sprintId) {
        Sprint sprint = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new RuntimeException("Sprint not found"));

        sprint.setStatus(SprintStatus.ENDED);
        sprintRepository.save(sprint);
    }
}
