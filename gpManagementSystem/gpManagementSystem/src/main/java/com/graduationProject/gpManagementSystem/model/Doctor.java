package com.graduationProject.gpManagementSystem.model;

import com.graduationProject.gpManagementSystem.enums.Role;
import com.graduationProject.gpManagementSystem.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Entity
@Builder
@NoArgsConstructor
@Table(name = "doctors")

public class Doctor extends User{
    
    @Id
    @GeneratedValue
    private Long id;
    private String specialization;
    
    @Builder(builderMethodName = "doctorBuilder")
    public Doctor(Long id, String username, String email, String password, Role role, Status status, Long id2,
            String specialization) {
        super(id, username, email, password, role, status);
        id = id2;
        this.specialization = specialization;
    }

    public Doctor(Long id, String specialization) {
        this.id = id;
        this.specialization = specialization;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }


    
}
