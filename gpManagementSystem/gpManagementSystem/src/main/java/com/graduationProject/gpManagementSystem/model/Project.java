package com.graduationProject.gpManagementSystem.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.graduationProject.gpManagementSystem.enums.ProjectStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "projects") // Table for projects
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Project {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private ProjectStatus ProjectStatus;
    private LocalDate startDate;
    private LocalDate endDate;
    
    @JsonIgnore
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> tasks;


    @OneToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;



    @JsonIgnore
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Sprint> sprints;


    public Project(){}


    public Project(Long id, String title, String description,
            com.graduationProject.gpManagementSystem.enums.ProjectStatus projectStatus, LocalDate startDate,
            LocalDate endDate, List<Task> tasks, Team team , List<Sprint> sprints) {
        this.id = id;
        this.title = title;
        this.description = description;
        ProjectStatus = projectStatus;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tasks = tasks;
        this.team = team;
        this.sprints = sprints;
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


    public ProjectStatus getProjectStatus() {
        return ProjectStatus;
    }


    public void setProjectStatus(ProjectStatus projectStatus) {
        ProjectStatus = projectStatus;
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


    public List<Task> getTasks() {
        return tasks;
    }


    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }


    public Team getTeam() {
        return team;
    }


    public void setTeam(Team team) {
        this.team = team;
    }


    public List<Sprint> getSprints() {
        return sprints;
    }


    public void setSprints(List<Sprint> sprints) {
        this.sprints = sprints;
    }
    

}
