package com.graduationProject.gpManagementSystem.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graduationProject.gpManagementSystem.model.Doctor;
import com.graduationProject.gpManagementSystem.service.DoctorService;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // API to fetch all doctors
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }
}
