package com.graduationProject.gpManagementSystem.service;

import com.graduationProject.gpManagementSystem.dto.TaskRequest;
import com.graduationProject.gpManagementSystem.enums.TaskStatus;
import com.graduationProject.gpManagementSystem.model.*;
import com.graduationProject.gpManagementSystem.repository.StudentRepository;
import com.graduationProject.gpManagementSystem.repository.TaskRepository;
import com.graduationProject.gpManagementSystem.repository.ProjectRepository;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final StudentRepository studentRepository;
    // private final SprintRepository sprintRepository;
    private final ProjectRepository projectRepository;


    public TaskService(TaskRepository taskRepository, StudentRepository studentRepository,  ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.studentRepository = studentRepository;
        this.projectRepository = projectRepository;
    }






    public Task createTask(TaskRequest taskRequest) {
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setTaskStatus(TaskStatus.TODO);
    
        // ✅ Fetch the Sprint entity before assigning it
        // Sprint sprint = sprintRepository.findById(taskRequest.getSprintId())
        //         .orElseThrow(() -> new RuntimeException("Sprint not found"));
        // task.setSprint(sprint);
    
        // ✅ Fetch the Project entity before assigning it
        Project project = projectRepository.findById(taskRequest.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));
        task.setProject(project);

    
        return taskRepository.save(task);
    }


    








    // ✅ Get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }





    // ✅ Get tasks by status
    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByTaskStatus(status);
    }







    // ✅ Assign a task to a student (Only the team leader of the sprint can do this)
    public void assignTask(Long taskId, Long studentId, Long teamLeaderId) {
        // Fetch the Team Leader
        Student teamLeader = studentRepository.findById(teamLeaderId)
                .orElseThrow(() -> new RuntimeException("Team Leader not found"));

        // Validate if the requester is actually a Team Leader
        if (!Boolean.TRUE.equals(teamLeader.isTeamLeader())) {
            throw new RuntimeException("Only Team Leaders can assign tasks.");
        }

        // Fetch the task
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Fetch the student who will be assigned the task
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Assign the task
        task.setStudent(student);
        taskRepository.save(task);
    

        // Assign task to student
        task.setStudent(student);
        taskRepository.save(task);
    }













    // ✅ Update a task
    public Task updateTask(Long taskId, TaskRequest taskRequest) {
        Optional<Task> taskOpt = taskRepository.findById(taskId);
        if (taskOpt.isEmpty()) {
            throw new RuntimeException("Task not found.");
        }

        Task task = taskOpt.get();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setTaskStatus(TaskStatus.TODO);

        return taskRepository.save(task);
    }









    // ✅ Delete a task
    public void deleteTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new RuntimeException("Task not found.");
        }
        taskRepository.deleteById(taskId);
    }










// ✅ Get tasks assigned to a specific student by status
public List<Task> getTasksByStudentAndStatus(Long studentId, TaskStatus status) {
    return taskRepository.findByStudentIdAndTaskStatus(studentId, status);
}



public Optional<Task> getTaskById(Long id){
    return taskRepository.findById(id);
}



 // ✅ Update Task Status
 public void updateTaskStatus(Long taskId, TaskStatus status) {
    Task task = taskRepository.findById(taskId)
            .orElseThrow(() -> new RuntimeException("Task not found"));

    task.setTaskStatus(status);
    taskRepository.save(task);
}




// ✅ Get Tasks by Status for a Sprint
public List<Task> getTasksBySprintAndStatus(Long sprintId, TaskStatus status) {
    return taskRepository.findBySprintIdAndTaskStatus(sprintId, status);
}

}
