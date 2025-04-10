package com.graduationProject.gpManagementSystem.repository;

import com.graduationProject.gpManagementSystem.model.TaskHistory;
import com.graduationProject.gpManagementSystem.model.Student;
import com.graduationProject.gpManagementSystem.model.Sprint;
import com.graduationProject.gpManagementSystem.model.Project;
import com.graduationProject.gpManagementSystem.enums.TaskStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Long> {
    List<TaskHistory> findByStudent(Student student);
    List<TaskHistory> findByStudentAndSprint(Student student, Sprint sprint);
    List<TaskHistory> findByStudentAndProject(Student student, Project project);
    Long countByStudentAndTaskStatus(Student student, TaskStatus status);
    Long countByStudent(Student student);
}
