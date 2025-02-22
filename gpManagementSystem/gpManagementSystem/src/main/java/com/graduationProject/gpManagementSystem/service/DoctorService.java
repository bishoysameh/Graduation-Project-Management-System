package com.graduationProject.gpManagementSystem.service;

import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graduationProject.gpManagementSystem.model.Doctor;
import com.graduationProject.gpManagementSystem.repository.DoctorRepository;

@Service
public class DoctorService {

 private final DoctorRepository doctorRepository;

 public DoctorService(DoctorRepository doctorRepository) {
    this.doctorRepository = doctorRepository;
}

// // Get all doctors
//     public List<Doctor> getAllDoctors() {
//         return doctorRepository.findByRole("DOCTOR");  // Fetch only users with role = DOCTOR
//     }


    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();  
    }
}
