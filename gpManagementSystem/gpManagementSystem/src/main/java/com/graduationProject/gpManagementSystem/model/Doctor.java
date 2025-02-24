package com.graduationProject.gpManagementSystem.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.graduationProject.gpManagementSystem.enums.Role;
import com.graduationProject.gpManagementSystem.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Entity
@Builder
@NoArgsConstructor
@Table(name = "doctors")


//dont forget getter and setter and constructors 
public class Doctor extends User{
    
    // @Id
    // @GeneratedValue
    // private Long id;

    private String specialization;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Team> teams;


    @ManyToMany(mappedBy = "doctors" , cascade = CascadeType.ALL)
    private List<Proposal> proposals ;

    /////////////////////////////////
     
    // i added proposals and change constructors to add new ones
    
    /// ////////////////////////////////
 


    @Builder(builderMethodName = "doctorBuilder")
    public Doctor(Long id, String username, String email, String password, Role role, Status status,
            String specialization, List<Team> teams, List<Proposal> proposals) {
        super(id, username, email, password, role, status);
        this.specialization = specialization;
        this.teams = teams;
        this.proposals = proposals;
    }


    public Doctor(Long id, String username, String email, String password, Role role, Status status,
            String specialization, List<Team> teams) {
        super(id, username, email, password, role, status);
        this.specialization = specialization;
        this.teams = teams;
    }

       
    public Doctor(String specialization, List<Team> teams, List<Proposal> proposals) {
        this.specialization = specialization;
        this.teams = teams;
        this.proposals = proposals;
    }


    public Doctor(String specialization, List<Team> teams) {
        this.specialization = specialization;
        this.teams = teams;
    }


    public Doctor(Long id, String username, String email, String password, Role role, Status status, Long id2,
    String specialization) {
super(id, username, email, password, role, status);
id = id2;
this.specialization = specialization;
}


    public Doctor( String specialization) {
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }


    public List<Team> getTeams() {
        return teams;
    }


    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }


    public List<Proposal> getProposals() {
        return proposals;
    }


    public void setProposals(List<Proposal> proposals) {
        this.proposals = proposals;
    }

    // public Long getId() {
    //     return super.getId();
    // }
    
}
