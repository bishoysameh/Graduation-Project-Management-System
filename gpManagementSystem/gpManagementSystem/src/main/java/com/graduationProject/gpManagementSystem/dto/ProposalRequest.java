package com.graduationProject.gpManagementSystem.dto;
import java.util.List;

public class ProposalRequest {
    //student id , change it to send in end point if you need it  
    private Long id;
    private String title;
    private String description;
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

    public List<Long> getDoctorIds() {
        return doctorIds;
    }
    public void setDoctorIds(List<Long> doctorIds) {
        this.doctorIds = doctorIds;
    }

    // Getters and Setters

}
