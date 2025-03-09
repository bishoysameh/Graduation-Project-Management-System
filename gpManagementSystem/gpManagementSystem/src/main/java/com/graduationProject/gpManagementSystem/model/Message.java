package com.graduationProject.gpManagementSystem.model;

import java.time.LocalDateTime;

import com.graduationProject.gpManagementSystem.enums.MessageType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue
    private Long id;

    private String content;
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private MessageType type;


    @ManyToOne
    @JoinColumn(name = "sender_id" , referencedColumnName = "id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "chat_room_id" ,  referencedColumnName = "id")
    private ChatRoom chatRoom;
    
}
