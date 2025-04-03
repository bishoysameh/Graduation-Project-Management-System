package com.graduationProject.gpManagementSystem.controller;

import com.graduationProject.gpManagementSystem.dto.ApiResponse;
import com.graduationProject.gpManagementSystem.dto.TaskRequest;
import com.graduationProject.gpManagementSystem.model.Task;
import com.graduationProject.gpManagementSystem.enums.TaskStatus;
import com.graduationProject.gpManagementSystem.service.TaskService;

import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ApiResponse<Task>> createTask(@RequestBody TaskRequest taskRequest) {
        Task createdTask =  taskService.createTask(taskRequest);
         ApiResponse<Task> response = new ApiResponse<>(
            "success",                   
            "Task created successfully",   
            createdTask                         
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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
    public ResponseEntity<ApiResponse<Task>> assignTask(@PathVariable Long taskId, @PathVariable Long studentId , @PathVariable Long teamLeaderId) {
       Task task =  taskService.assignTask(taskId, studentId , teamLeaderId);
       ApiResponse<Task> response = new ApiResponse<>(
        "success", 
        "Task successfully assigned to the student", 
        task
    );
    return ResponseEntity.status(HttpStatus.OK).body(response);    }







    // ✅ Update a task
    @PutMapping("/{taskId}")
    public ResponseEntity<ApiResponse<Task>> updateTask(@PathVariable Long taskId, @RequestBody TaskRequest taskRequest) {
        Task updatedTask =  taskService.updateTask(taskId, taskRequest);
        ApiResponse<Task> response = new ApiResponse<>(
            "success",
            "Task updated successfully",
            updatedTask  
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }







    // ✅ Delete a task
    @DeleteMapping("/{taskId}")
    public ResponseEntity<ApiResponse<Void>> deleteTask(@PathVariable Long taskId) {
         taskService.deleteTask(taskId);
         ApiResponse<Void> response = new ApiResponse<>(
            "success",
            "Task deleted successfully"
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
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
    public ResponseEntity<ApiResponse<Task>> updateTaskStatus(@PathVariable Long taskId, @PathVariable TaskStatus status) {
        Task updatedTask =  taskService.updateTaskStatus(taskId, status);
        ApiResponse<Task> response = new ApiResponse<>(
            "success",
            "Task status updated successfully",
            updatedTask 
    );
    return ResponseEntity.status(HttpStatus.OK).body(response);
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
