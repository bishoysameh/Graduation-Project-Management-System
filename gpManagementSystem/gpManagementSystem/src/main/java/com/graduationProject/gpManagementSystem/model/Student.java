package com.graduationProject.gpManagementSystem.model;
import com.graduationProject.gpManagementSystem.enums.Role;
import com.graduationProject.gpManagementSystem.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.*;


// import jakarta.persistence.Table;
@Entity
@Builder
@NoArgsConstructor
@Table(name = "students") // Table for Student

public class Student extends User{
    
   
//kant long wna 5letha Long
    private int year ;
    private String department;


    public Student(int year, String department) {
        this.year = year;
        this.department = department;
    }

    @Builder(builderMethodName = "studentBuilder")
    public Student(Long id, String username, String email, String password, Role role, Status status, int year,
            String department) {
        super(id, username, email, password, role, status);
        this.year = year;
        this.department = department;
    }
    public int getYear() {
        return year;
    }
    public String getDepartment() {
        return department;
    }


    public void setYear(int year) {
        this.year = year;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Student [year=" + year + ", department=" + department + ", getEmail()=" + getEmail() + ", getId()="
                + getId() + ", getRole()=" + getRole() + ", getStatus()=" + getStatus() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + ", getYear()=" + getYear() + ", getDepartment()="
                + getDepartment() + "]";
    }

    
    
}
