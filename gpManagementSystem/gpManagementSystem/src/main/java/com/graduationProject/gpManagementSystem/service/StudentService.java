package com.graduationProject.gpManagementSystem.service;

import com.graduationProject.gpManagementSystem.model.Student;
import com.graduationProject.gpManagementSystem.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Optional<Student> findByStudentId(String studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    public List<Student> findByStudentIds(List<String> studentIds) {
        return studentRepository.findByStudentIdIn(studentIds);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
}
