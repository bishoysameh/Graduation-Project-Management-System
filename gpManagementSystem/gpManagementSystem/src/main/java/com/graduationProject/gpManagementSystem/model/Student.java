package com.graduationProject.gpManagementSystem.model;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.graduationProject.gpManagementSystem.enums.Role;
import com.graduationProject.gpManagementSystem.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;


// import jakarta.persistence.Table;
@Entity
@Builder
@NoArgsConstructor
@Table(name = "students") // Table for Student

//dont forget getter and setter and constructors

public class Student extends User{
    
   
//kant long wna 5letha Long
    @Column(nullable = false)
    private int level ;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false, unique = true)
    private String studentId;

    private double gpa;

    @Column(nullable = false)
    private int creditHours;
    
    private boolean isTeamLeader ;

//Define the many-to-one relationship with Team
    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Task> tasks;

    public Student(int level, String department, String studentId, double gpa, int creditHours , boolean isTeamLeader) {
        this.level = level;
        this.department = department;
        this.studentId = studentId;
        this.gpa = gpa;
        this.creditHours = creditHours;
        this.isTeamLeader = isTeamLeader;
    }

    @Builder(builderMethodName = "studentBuilder")
    public Student(Long id, String username, String email, String password, Role role, Status status, int level,
            String department, String studentId, double gpa, int creditHours,boolean isTeamLeader , List<ChatRoom> chatRooms , List<Message> messages) {
        super(id, username, email, password, role, status, chatRooms , messages);
        this.level = level;
        this.department = department;
        this.studentId = studentId;
        this.gpa = gpa;
        this.creditHours = creditHours;
        this.isTeamLeader = isTeamLeader;
    }
    

    public Student(int level, String department, String studentId, double gpa, int creditHours,boolean isTeamLeader, Team team,
            List<Task> tasks) {
        this.level = level;
        this.department = department;
        this.studentId = studentId;
        this.gpa = gpa;
        this.creditHours = creditHours;
        this.isTeamLeader = isTeamLeader;
        this.team = team;
        this.tasks = tasks;
    }

    public Student(Long id, String username, String email, String password, Role role, Status status, int level,
            String department, String studentId, double gpa, int creditHours, Team team,
            List<Task> tasks , List<ChatRoom> chatRooms , List<Message> messages) {
        super(id, username, email, password, role, status, chatRooms , messages);
        this.level = level;
        this.department = department;
        this.studentId = studentId;
        this.gpa = gpa;
        this.creditHours = creditHours;
        this.team = team;
        this.tasks = tasks;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }


    

    public boolean isTeamLeader() {
        return isTeamLeader;
    }

    public void setTeamLeader(boolean isTeamLeader) {
        this.isTeamLeader = isTeamLeader;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    // public List<Task> getStudentTasks() {
    //     return tasks;
    // }

    // public void setStudentTasks(List<Task> tasks) {
    //     this.tasks = tasks;
    // }

    @Override
    public String toString() {
        return "Student [level=" + level + ", department=" + department + ", studentId=" + studentId + ", gpa=" + gpa
                + ", creditHours=" + creditHours + ", getEmail()=" + getEmail() + ", getId()=" + getId()
                + ", getRole()=" + getRole() + ", getStatus()=" + getStatus() + ", getLevel()=" + getLevel()
                + ", getDepartment()=" + getDepartment() + ", getStudentId()=" + getStudentId() + ", getGpa()="
                + getGpa() + ", getCreditHours()=" + getCreditHours() + "]";
    }


    // public Student(int year, String department) {
    //     this.year = year;
    //     this.department = department;
    // }

    // @Builder(builderMethodName = "studentBuilder")
    // public Student(Long id, String username, String email, String password, Role role, Status status, int year,
    //         String department) {
    //     super(id, username, email, password, role, status);
    //     this.year = year;
    //     this.department = department;
    // }
 
    // public String getDepartment() {
    //     return department;
    // }


 
    // public void setDepartment(String department) {
    //     this.department = department;
    // }

    // @Override
    // public String toString() {
    //     return "Student [year=" + year + ", department=" + department + ", getEmail()=" + getEmail() + ", getId()="
    //             + getId() + ", getRole()=" + getRole() + ", getStatus()=" + getStatus() + ", hashCode()=" + hashCode()
    //             + ", toString()=" + super.toString() + ", getYear()=" + getYear() + ", getDepartment()="
    //             + getDepartment() + "]";
    // }

    
    
}
