package com.graduationProject.gpManagementSystem.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.graduationProject.gpManagementSystem.model.ChatRoom;
import com.graduationProject.gpManagementSystem.model.Message;
import com.graduationProject.gpManagementSystem.service.ChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    // This is for HTTP API, NOT WebSocket
    // @PostMapping("/send")
    // public ResponseEntity<Message> sendMessage(@RequestBody MessageRequestDto request) {
    //     Message message = chatService.sendMessage(request.getChatRoomId(), request.getSenderId(), request.getContent());
    //     return ResponseEntity.ok(message);
    // }

    // Fetch chat messages for a chat room
    @GetMapping("/{chatRoomId}/messages")
    public ResponseEntity<List<Message>> getChatMessages(@PathVariable Long chatRoomId) {
        return ResponseEntity.ok(chatService.getChatMessages(chatRoomId));
    }

    // Fetch chat rooms for a user
    @GetMapping("/rooms/{userId}")
    public ResponseEntity<List<ChatRoom>> getUserChatRooms(@PathVariable Long userId) {
        return ResponseEntity.ok(chatService.getUserChatRooms(userId));
    }
}

























// package com.graduationProject.gpManagementSystem.controller;

// import java.util.List;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.graduationProject.gpManagementSystem.dto.MessageRequestDto;
// import com.graduationProject.gpManagementSystem.model.ChatRoom;
// import com.graduationProject.gpManagementSystem.model.Message;
// import com.graduationProject.gpManagementSystem.service.ChatService;

// import lombok.RequiredArgsConstructor;

// @RestController
// @RequestMapping("/api/chat")
// @RequiredArgsConstructor
// public class ChatController {
//     private final ChatService chatService;

//     // REST API to send a message (Request Body)
//     @PostMapping("/send")
//     public ResponseEntity<Message> sendMessage(@RequestBody MessageRequestDto request) {
//         Message message = chatService.sendMessage(request.getChatRoomId(), request.getSenderId(), request.getContent());
//         return ResponseEntity.ok(message);
//     }

//     // REST API to get chat messages
//     @GetMapping("/{chatRoomId}/messages")
//     public ResponseEntity<List<Message>> getChatMessages(@PathVariable Long chatRoomId) {
//         return ResponseEntity.ok(chatService.getChatMessages(chatRoomId));
//     }



//     //mmkn error
//       @GetMapping("/rooms/{userId}")
//     public ResponseEntity<List<ChatRoom>> getUserChatRooms(@PathVariable Long userId) {
//         return ResponseEntity.ok(chatService.getUserChatRooms(userId));
//     }
// }
//*************************************************************************************************************** */


