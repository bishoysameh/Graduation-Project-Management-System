package com.graduationProject.gpManagementSystem.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "proposals")
public class Proposal {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private boolean approved = false;

    @ManyToOne
    @JoinColumn(name = "team_id" , referencedColumnName = "id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "submitted_by" ,  referencedColumnName = "id")
    private Student submittedBy;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
        name = "proposal_doctors",
        joinColumns = @JoinColumn(name = "proposal_id"),
        inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private List<Doctor> doctors; // Send proposal to multiple doctors

    private String pdfPath; // Store file path
    private String filename;

    public Proposal(){}
    

    // public Proposal(Long id, String title, String description,  boolean approved,
    //         Team team, Student submittedBy, List<Doctor> doctors, String pdfPath) {
    //     this.id = id;
    //     this.title = title;
    //     this.description = description;
    //     this.approved = approved;
    //     this.team = team;
    //     this.submittedBy = submittedBy;
    //     this.doctors = doctors;
    //     this.pdfPath = pdfPath;
    // }

    public Proposal(Long id, String title, String description, boolean approved, Team team, Student submittedBy,
            List<Doctor> doctors, String pdfPath, String filename) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.approved = approved;
        this.team = team;
        this.submittedBy = submittedBy;
        this.doctors = doctors;
        this.pdfPath = pdfPath;
        this.filename = filename;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Student getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(Student submittedBy) {
        this.submittedBy = submittedBy;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }


    public String getFilename() {
        return filename;
    }


    public void setFilename(String filename) {
        this.filename = filename;
    }

    // Getters and Setters




}
