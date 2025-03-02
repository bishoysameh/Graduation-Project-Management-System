package com.graduationProject.gpManagementSystem.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.graduationProject.gpManagementSystem.enums.SprintStatus;

@Entity
@Table(name = "sprints")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Sprint {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description; // Sprint description

    @Column(nullable = false)
    private LocalDateTime startTime; // Sprint starts immediately when created

    @Column(nullable = false)
    private LocalDateTime endTime; // Calculated based on duration

    @Column(nullable = false)
    private int durationInWeeks; // Sprint duration (set by team leader)

    @Enumerated(EnumType.STRING)
    private SprintStatus status; // Sprint status (ACTIVE, COMPLETED)



    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project; // Each sprint belongs to one project


    @OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL)
    private List<Task> tasks; // A sprint has multiple tasks

    // Constructors
    public Sprint() {}

    public Sprint(String name, String description, int durationInWeeks, Project project , List<Task> tasks) {
        this.name = name;
        this.description = description;
        this.startTime = LocalDateTime.now(); // Sprint starts immediately
        this.durationInWeeks = durationInWeeks;
        this.endTime = this.startTime.plusWeeks(durationInWeeks); // End time calculated
        this.status = SprintStatus.ACTIVE;
        this.project = project;
        this.tasks = tasks;
    }


    public Sprint(String name, String description, Project project,LocalDateTime startTime, LocalDateTime endTime,
            SprintStatus status,  List<Task> tasks) {
        this.name = name;
        this.description = description;
        this.project = project;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.tasks = tasks;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getDurationInWeeks() {
        return durationInWeeks;
    }

    public void setDurationInWeeks(int durationInWeeks) {
        this.durationInWeeks = durationInWeeks;
        this.endTime = this.startTime.plusWeeks(durationInWeeks); // Recalculate end time

    }

    public SprintStatus getStatus() {
        return status;
    }

    public void setStatus(SprintStatus status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }














    

    // Getters and Setters
    // public Long getId() { return id; }

    // public String getName() { return name; }
    // public void setName(String name) { this.name = name; }

    // public String getDescription() { return description; }
    // public void setDescription(String description) { this.description = description; }

    // public LocalDateTime getStartTime() { return startTime; }

    // public LocalDateTime getEndTime() { return endTime; }

    // public int getDurationInWeeks() { return durationInWeeks; }
    // public void setDurationInWeeks(int durationInWeeks) {
    //     this.durationInWeeks = durationInWeeks;
    //     this.endTime = this.startTime.plusWeeks(durationInWeeks); // Recalculate end time
    // }

    // public SprintStatus getStatus() { return status; }
    // public void setStatus(SprintStatus status) { this.status = status; }

    // public Project getProject() { return project; }
    // public void setProject(Project project) { this.project = project; }

    // public List<Task> getTasks() { return tasks; }
    // public void setTasks(List<Task> tasks) { this.tasks = tasks; }
}
