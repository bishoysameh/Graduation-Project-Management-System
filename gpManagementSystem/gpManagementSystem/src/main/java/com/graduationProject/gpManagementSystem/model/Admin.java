package com.graduationProject.gpManagementSystem.model;

import java.util.List;

import com.graduationProject.gpManagementSystem.enums.Role;
import com.graduationProject.gpManagementSystem.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@Table(name = "admins")
public class Admin extends User {
    // private Long id;

    @Builder(builderMethodName = "doctorBuilder")
    public Admin(Long id, String username, String email, String password, Role role, Status status, Long id2 ,List<ChatRoom> chatRooms , List<Message> messages) {
        super(id, username, email, password, role, status , chatRooms , messages);
        id = id2;
    }

    // public Admin(Long id) {
    //     this.id = id;
    // }

    // public Long getId() {
    //     return id;
    // }

    // public void setId(Long id) {
    //     this.id = id;
    // }
    
}
