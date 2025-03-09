package com.graduationProject.gpManagementSystem.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.graduationProject.gpManagementSystem.model.ChatRoom;
import com.graduationProject.gpManagementSystem.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/chatrooms")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    // ✅ Create a new chat room
    @PostMapping("/create")
    public ResponseEntity<ChatRoom> createChatRoom(@RequestBody ChatRoom chatRoom) {
        return ResponseEntity.ok(chatRoomService.createChatRoom(chatRoom));
    }

    // ✅ Get a chat room by ID
    @GetMapping("/{id}")
    public ResponseEntity<ChatRoom> getChatRoomById(@PathVariable Long id) {
        return ResponseEntity.ok(chatRoomService.getChatRoomById(id));
    }

    // ✅ Get all chat rooms
    @GetMapping
    public ResponseEntity<List<ChatRoom>> getAllChatRooms() {
        return ResponseEntity.ok(chatRoomService.getAllChatRooms());
    }

    // ✅ Update a chat room
    @PutMapping("/{id}")
    public ResponseEntity<ChatRoom> updateChatRoom(@PathVariable Long id, @RequestBody ChatRoom chatRoom) {
        return ResponseEntity.ok(chatRoomService.updateChatRoom(id, chatRoom));
    }

    // ✅ Delete a chat room
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChatRoom(@PathVariable Long id) {
        chatRoomService.deleteChatRoom(id);
        return ResponseEntity.ok("Chat room deleted successfully");
    }




    @PostMapping("/{chatRoomId}/addUser/{userId}")
    public ResponseEntity<String> addUserToChatRoom(@PathVariable Long chatRoomId, @PathVariable Long userId) {
        chatRoomService.addUserToChatRoom(chatRoomId, userId);
        return ResponseEntity.ok("User added to chat room successfully.");
    }
}
