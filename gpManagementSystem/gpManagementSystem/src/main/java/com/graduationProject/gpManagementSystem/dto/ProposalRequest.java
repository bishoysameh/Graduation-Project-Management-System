package com.graduationProject.gpManagementSystem.dto;

import java.time.LocalDate;
import java.util.List;

public class ProposalRequest {
    private Long id;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Long> doctorIds;
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
    public List<Long> getDoctorIds() {
        return doctorIds;
    }
    public void setDoctorIds(List<Long> doctorIds) {
        this.doctorIds = doctorIds;
    }

    // Getters and Setters

}
