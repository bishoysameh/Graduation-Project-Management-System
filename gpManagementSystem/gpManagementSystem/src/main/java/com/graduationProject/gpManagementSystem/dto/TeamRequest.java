package com.graduationProject.gpManagementSystem.dto;

import java.util.List;

public class TeamRequest {
    private String name;
    private List<String> studentIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<String> studentIds) {
        this.studentIds = studentIds;
    }
}
