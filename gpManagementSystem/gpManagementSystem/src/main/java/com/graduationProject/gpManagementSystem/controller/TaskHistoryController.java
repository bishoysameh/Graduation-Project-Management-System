package com.graduationProject.gpManagementSystem.controller;

import com.graduationProject.gpManagementSystem.model.Student;
import com.graduationProject.gpManagementSystem.service.TaskHistoryService;
import com.graduationProject.gpManagementSystem.dto.PerformanceDTO;
import com.graduationProject.gpManagementSystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task-history")
public class TaskHistoryController {

    @Autowired
    private TaskHistoryService taskHistoryService;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/performance/{studentId}")
    public PerformanceDTO getStudentPerformance(@PathVariable Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return taskHistoryService.calculateStudentPerformance(student);
    }
}
