package com.graduationProject.gpManagementSystem.service;

import com.graduationProject.gpManagementSystem.model.*;
import com.graduationProject.gpManagementSystem.dto.PerformanceDTO;
import com.graduationProject.gpManagementSystem.enums.TaskStatus;
import com.graduationProject.gpManagementSystem.repository.TaskHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskHistoryService {

    @Autowired
    private TaskHistoryRepository taskHistoryRepository;

    public List<TaskHistory> getAllHistoriesForStudent(Student student) {
        return taskHistoryRepository.findByStudent(student);
    }

    public PerformanceDTO calculateStudentPerformance(Student student) {
        long totalTasks = taskHistoryRepository.countByStudent(student);
        long doneTasks = taskHistoryRepository.countByStudentAndTaskStatus(student, TaskStatus.DONE);

        double percentage = totalTasks > 0 ? (doneTasks * 100.0 / totalTasks) : 0.0;

        return new PerformanceDTO(student.getId(), student.getUserName(), doneTasks, totalTasks, percentage);
    }
}
