package com.graduationProject.gpManagementSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graduationProject.gpManagementSystem.model.Doctor;
import com.graduationProject.gpManagementSystem.service.DoctorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // API to fetch all doctors
    //work
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }
//work
    @GetMapping("/{id}")
    public Optional<Doctor> getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    //delete it because it is the same register doctor 
    @PostMapping
    public void addDoctor(@RequestBody Doctor doctor) {
         doctorService.addDoctor(doctor);
    }

    //contain error
    @PutMapping("/{id}")
    public Doctor updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {        
        return doctorService.updateDoctor(id,doctor);
    }

    //work
    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Long id ){
         doctorService.deleteDoctor(id);
    }
    


    
  
}
