package com.graduationProject.gpManagementSystem.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.graduationProject.gpManagementSystem.enums.Department;
import com.graduationProject.gpManagementSystem.enums.Role;
import com.graduationProject.gpManagementSystem.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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


    @Enumerated(EnumType.STRING)
    private Department department;

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
            String specialization, Department department ,List<Team> teams, List<Proposal> proposals , List<ChatRoom> chatRooms ,List<Message> messages) {
        super(id, username, email, password, role, status, chatRooms , messages );
        this.specialization = specialization;
        this.department = department;
        this.teams = teams;
        this.proposals = proposals;
    }


    public Doctor(Long id, String username, String email, String password, Role role, Status status,
            String specialization,Department department, List<Team> teams , List<ChatRoom> chatRooms , List<Message> messages) {
        super(id, username, email, password, role, status , chatRooms , messages);
        this.specialization = specialization;
        this.department = department;
        this.teams = teams;
    }

       
    public Doctor(String specialization,Department department, List<Team> teams, List<Proposal> proposals) {
        this.specialization = specialization;
        this.department = department;
        this.teams = teams;
        this.proposals = proposals;
    }


    public Doctor(String specialization,Department department, List<Team> teams) {
        this.specialization = specialization;
        this.department = department;
        this.teams = teams;
    }


    public Doctor(Long id, String username, String email, String password, Role role, Status status, Long id2,
    String specialization ,Department department, List<ChatRoom> chatRooms , List<Message> messages) {
super(id, username, email, password, role, status , chatRooms , messages);
id = id2;
this.specialization = specialization;
this.department = department;
}


    public Doctor( String specialization , Department department) {
        this.specialization = specialization;
        this.department = department;
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


    public Department getDepartment() {
        return department;
    }


    public void setDepartment(Department department) {
        this.department = department;
    }

    // public Long getId() {
    //     return super.getId();
    // }
    
}
