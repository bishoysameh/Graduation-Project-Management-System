package com.graduationProject.gpManagementSystem.repository;

import com.graduationProject.gpManagementSystem.enums.TaskStatus;
import com.graduationProject.gpManagementSystem.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProjectId(Long projectId);
    List<Task> findBySprintId(Long sprintId);
    List<Task> findByStudentIdAndTaskStatus(Long studentId, TaskStatus taskStatus);
    List<Task> findByTaskStatus(TaskStatus taskStatus);
    List<Task> findBySprintIdAndTaskStatus(Long sprintId, TaskStatus status);



}
