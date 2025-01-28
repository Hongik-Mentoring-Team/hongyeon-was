package com.hongik.mentor.hongik_mentor.controller;

import com.hongik.mentor.hongik_mentor.controller.dto.chat.ChatInitiateDto;
import com.hongik.mentor.hongik_mentor.controller.dto.chat.ChatMessageDto;
import com.hongik.mentor.hongik_mentor.controller.dto.chat.ChatRoomDto;
import com.hongik.mentor.hongik_mentor.controller.dto.chat.ChatRoomMemberRequestDto;
import com.hongik.mentor.hongik_mentor.exception.ErrorCode;
import com.hongik.mentor.hongik_mentor.exception.ErrorResponseEntity;
import com.hongik.mentor.hongik_mentor.exception.InitiateChatException;
import com.hongik.mentor.hongik_mentor.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/message")//클라이언트가 /app/chat/message로 메시지 보냈을 때
    public void broadCastMessage(@Payload ChatMessageDto messageDto) {
        chatService.saveChatMessage(messageDto.getChatRoomId(), messageDto);
        Long chatRoomId = messageDto.getChatRoomId();

        //해당 채팅방으로 메시지 전송
        //메시지를 /topic/chat/room로 브로드캐스팅
        messagingTemplate.convertAndSend("/topic/chat/" + chatRoomId, messageDto);
    }

    //채팅방 및 채팅 참여자 생성
    @PostMapping("/chatRoom/initiate")
    public ResponseEntity<Map<String,Long>> initiateChat(@RequestBody ChatInitiateDto requestDto) {
        //create ChatRoom
        String roomName = requestDto.getRoomName();
        Long chatRoomId = chatService.saveChatRoom(new ChatRoomDto(roomName));
        //create ChatRoomMember
        chatService.saveChatRoomMembers(chatRoomId, requestDto.getMembersInfo());

        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap("roomId", chatRoomId));
    }

}
