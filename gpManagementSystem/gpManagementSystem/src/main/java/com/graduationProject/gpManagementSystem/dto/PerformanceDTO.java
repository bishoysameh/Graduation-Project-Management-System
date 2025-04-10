package com.graduationProject.gpManagementSystem.dto;

public class PerformanceDTO {
    private Long studentId;
    private String studentName;
    private long doneTasks;
    private long totalTasks;
    private double percentage;

    public PerformanceDTO(Long studentId, String studentName, long doneTasks, long totalTasks, double percentage) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.doneTasks = doneTasks;
        this.totalTasks = totalTasks;
        this.percentage = percentage;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public long getDoneTasks() {
        return doneTasks;
    }

    public void setDoneTasks(long doneTasks) {
        this.doneTasks = doneTasks;
    }

    public long getTotalTasks() {
        return totalTasks;
    }

    public void setTotalTasks(long totalTasks) {
        this.totalTasks = totalTasks;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    // Getters and setters
}
