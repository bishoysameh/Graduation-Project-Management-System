package com.graduationProject.gpManagementSystem.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "teams") // Table for teams
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

//dont forget getter and setter and constructors
public class Team {
    @Id
    @GeneratedValue
    private Long id;
    private String name;


    // Define the one-to-many relationship with students

    //if you dont need retrive students in json add jsonIgnore to it 
    // @JsonIgnore
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Student> students;


    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;


    @OneToOne(mappedBy = "team")
    @JsonIgnore
    private Project project;
   
    public Team(){};


    public Team(Long id, String name, List<Student> students, Doctor doctor, Project project) {
        this.id = id;
        this.name = name;
        this.students = students;
        this.doctor = doctor;
        this.project = project;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public List<Student> getStudents() {
        return students;
    }


    public void setStudents(List<Student> students) {
        this.students = students;
    }


    public Doctor getDoctor() {
        return doctor;
    }


    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }


    public Project getProject() {
        return project;
    }


    public void setProject(Project project) {
        this.project = project;
    }

    
    
}
