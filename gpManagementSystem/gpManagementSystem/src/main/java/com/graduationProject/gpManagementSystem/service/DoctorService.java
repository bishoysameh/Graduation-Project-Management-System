package com.graduationProject.gpManagementSystem.service;

// import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graduationProject.gpManagementSystem.exception.ResourceNotFoundException;
import com.graduationProject.gpManagementSystem.model.Doctor;
import com.graduationProject.gpManagementSystem.repository.DoctorRepository;

@Service
public class DoctorService {

 private final DoctorRepository doctorRepository;

 public DoctorService(DoctorRepository doctorRepository) {
    this.doctorRepository = doctorRepository;
}

    // public List<Doctor> getAllDoctors() {
    //     return doctorRepository.findAll();  
    // }

    public Page<Doctor> getAllDoctors(Pageable pageable) {
        return doctorRepository.findAll(pageable);  
    }

   



    public Optional<Doctor> getDoctorById(Long id){
           return doctorRepository.findById(id);
    }


    //remove it because there is registerDoctor methon in authentication not need to this.
    public void addDoctor (Doctor doctor){
         doctorRepository.save(doctor);
    }

     // Update an existing doctor
    //  public Doctor updateDoctor(Long id, Doctor doctorDetails) {
    //     return doctorRepository.findById(id).map(doctor -> {
    //         doctor.setUserName(doctorDetails.getUsername());
    //         doctor.setSpecialization(doctorDetails.getSpecialization());
    //         doctor.setEmail(doctorDetails.getEmail());
    //         return doctorRepository.save(doctor);
    //     }).orElseThrow(() -> new RuntimeException("Doctor not found"));
    // }

public Doctor updateDoctor(Long id, Doctor doctorDetails) {
    Doctor updatedDoctor = doctorRepository.findById(id).map(doctor -> {
        doctor.setUserName(doctorDetails.getUsername());
        doctor.setSpecialization(doctorDetails.getSpecialization());
        doctor.setEmail(doctorDetails.getEmail());
        return doctorRepository.save(doctor);
    }).orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

    return updatedDoctor;
   
}




    // public void deleteDoctor(Long id ){
    //      doctorRepository.deleteById(id);
    // }
    
    public void deleteDoctor(Long id ){
         doctorRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        doctorRepository.deleteById(id);
        }



 }
