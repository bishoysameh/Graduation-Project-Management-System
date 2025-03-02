package com.graduationProject.gpManagementSystem.dto;

import java.util.List;

public class SprintRequest {
    // change it if tou need send it in api end point
    private Long projectId;
    private String name;
    private String description;
    private int durationWeeks;
    private List<Long> taskIds;



    public Long getProjectId() {
        return projectId;
    }
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
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
    public int getDurationWeeks() {
        return durationWeeks;
    }
    public void setDurationWeeks(int durationWeeks) {
        this.durationWeeks = durationWeeks;
    }
    public List<Long> getTaskIds() {
        return taskIds;
    }
    public void setTaskIds(List<Long> taskIds) {
        this.taskIds = taskIds;
    } 



    
}
