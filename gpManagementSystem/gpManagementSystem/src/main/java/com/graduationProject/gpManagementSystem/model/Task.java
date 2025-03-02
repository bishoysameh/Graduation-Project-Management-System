package com.graduationProject.gpManagementSystem.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.graduationProject.gpManagementSystem.enums.TaskStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "tasks") // Table for tasks
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Task {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;


    @ManyToOne
    @JoinColumn(name = "student_id" , referencedColumnName = "id")
    private Student student;


    @ManyToOne
    @JoinColumn(name = "sprint_id" , referencedColumnName = "id")
    private Sprint sprint;

    //if any problem occure remove this empty constructor
    public Task(){}


    public Task(Long id, String title, String description, TaskStatus taskStatus, LocalDate startDate,
            LocalDate endDate, Project project, Student student, Sprint sprint) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.taskStatus = taskStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.project = project;
        this.student = student;
        this.sprint = sprint;
    }


    public Task(Long id, String title, String description, LocalDate startDate, LocalDate endDate, Project project,
            Student student, Sprint sprint) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.project = project;
        this.student = student;
        this.sprint = sprint;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public LocalDate getStartDate() {
        return startDate;
    }


    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }


    public LocalDate getEndDate() {
        return endDate;
    }


    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }


//if any problem happend return those


    // public Student getStudentTasks() {
    //     return student;
    // }


    // public void setStudentTasks(Student student) {
    //     this.student = student;
    // }


    public Project getProject() {
        return project;
    }


    public void setProject(Project project) {
        this.project = project;
    }


    public Student getStudent() {
        return student;
    }


    public void setStudent(Student student) {
        this.student = student;
    }


    public Sprint getSprint() {
        return sprint;
    }


    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }


    public TaskStatus getTaskStatus() {
        return taskStatus;
    }


    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }


    

    
}
