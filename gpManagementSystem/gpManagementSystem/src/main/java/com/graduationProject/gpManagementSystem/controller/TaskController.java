package com.graduationProject.gpManagementSystem.controller;

import com.graduationProject.gpManagementSystem.dto.TaskRequest;
import com.graduationProject.gpManagementSystem.model.Task;
import com.graduationProject.gpManagementSystem.enums.TaskStatus;
import com.graduationProject.gpManagementSystem.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }




    // ✅ Create a new task
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskRequest taskRequest) {
        Task createdTask = taskService.createTask(taskRequest);
        return ResponseEntity.ok(createdTask);
    }




    // ✅ Get all tasks
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }






    // ✅ Get tasks by status 
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Task>> getTasksByStatus(@PathVariable TaskStatus status) {
        return ResponseEntity.ok(taskService.getTasksByStatus(status));
    }








    // ✅ Assign a task to a student (Only the team leader can do this)
    @PutMapping("/{taskId}/assignedTo/{studentId}/by/{teamLeaderId}")
    public ResponseEntity<String> assignTask(@PathVariable Long taskId, @PathVariable Long studentId , @PathVariable Long teamLeaderId) {
        taskService.assignTask(taskId, studentId , teamLeaderId);
        return ResponseEntity.ok("Task assigned successfully.");
    }







    // ✅ Update a task
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody TaskRequest taskRequest) {
        return ResponseEntity.ok(taskService.updateTask(taskId, taskRequest));
    }







    // ✅ Delete a task
    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok("Task deleted successfully.");
    }






        // ✅ Get tasks assigned to a specific student by status
        @GetMapping("/student/{studentId}/status/{status}")
        public ResponseEntity<List<Task>> getTasksByStudentAndStatus(@PathVariable Long studentId, @PathVariable TaskStatus status) {
            List<Task> tasks = taskService.getTasksByStudentAndStatus(studentId, status);
            return ResponseEntity.ok(tasks);
        }




        @GetMapping("/{id}")
        public Optional<Task> getTaskById(@PathVariable Long id){
            return taskService.getTaskById(id);
        }


  // ✅ Update Task Status
    @PutMapping("/{taskId}/status/{status}")
    public ResponseEntity<String> updateTaskStatus(@PathVariable Long taskId, @PathVariable TaskStatus status) {
        taskService.updateTaskStatus(taskId, status);
        return ResponseEntity.ok("Task status updated successfully.");
    }






    // ✅ Get Tasks by Status for a Sprint
    @GetMapping("/{sprintId}/tasks/{status}")
    public ResponseEntity<List<Task>> getTasksBySprintAndStatus(
            @PathVariable Long sprintId,
            @PathVariable TaskStatus status) {
        List<Task> tasks = taskService.getTasksBySprintAndStatus(sprintId, status);
        return ResponseEntity.ok(tasks);
    }

}
