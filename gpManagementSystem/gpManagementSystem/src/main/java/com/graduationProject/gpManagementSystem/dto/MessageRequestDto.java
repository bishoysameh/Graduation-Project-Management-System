package com.graduationProject.gpManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MessageRequestDto {
    private Long chatRoomId;
    private Long senderId;
    private String content;
}
