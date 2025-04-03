package com.graduationProject.gpManagementSystem.controller;





import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.graduationProject.gpManagementSystem.dto.ChatMessage;
import com.graduationProject.gpManagementSystem.service.ChatService;

@Controller
public class WebSocketChatController {

private final SimpMessagingTemplate messagingTemplate;

 private final ChatService chatService;
public WebSocketChatController(ChatService chatService , SimpMessagingTemplate messagingTemplate){
    this.chatService = chatService;
    this.messagingTemplate = messagingTemplate;
}

    @MessageMapping("/chat.sendMessage")
    // @SendTo("/topic/public")
    public ChatMessage sendMessage(
            @Payload ChatMessage chatMessage
    ) {

        // Notify only users in the specific chat room
    String destination = "/chatroom/" + chatMessage.getChatRoomId();
    messagingTemplate.convertAndSend(destination, chatMessage);


        chatService.sendMessage(chatMessage.getChatRoomId(), chatMessage.getSenderId(), chatMessage.getContent() , chatMessage.getType() );
        return chatMessage;
    }

    @SuppressWarnings("null")
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}

