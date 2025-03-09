package com.graduationProject.gpManagementSystem.dto;

import com.graduationProject.gpManagementSystem.enums.MessageType;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {

    private MessageType type;
    private String content;
    private String sender;
    
    private Long chatRoomId;
    private Long senderId;
}