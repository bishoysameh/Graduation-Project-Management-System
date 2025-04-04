package com.graduationProject.gpManagementSystem.dto;

import java.time.LocalDate;

import com.graduationProject.gpManagementSystem.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequest {
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long projectId;
    private Long sprintId;
    private Long studentId;
}
