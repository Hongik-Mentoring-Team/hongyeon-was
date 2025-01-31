package com.hongik.mentor.hongik_mentor.controller;

import com.hongik.mentor.hongik_mentor.constant.ConstantUri;
import com.hongik.mentor.hongik_mentor.controller.dto.chat.ChatInitiateDto;
import com.hongik.mentor.hongik_mentor.controller.dto.chat.ChatMessageDto;
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

@RequiredArgsConstructor
@RestController
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/message")//클라이언트가 /app/chat/message로 메시지 보냈을 때
    public void broadCastMessage(@Payload ChatMessageDto messageDto) {
        //메시지 저장
        chatService.saveChatMessage(messageDto.getChatRoomId(), messageDto);
        Long chatRoomId = messageDto.getChatRoomId();

        //해당 채팅방으로 메시지 전달
        //메시지를 url(/topic/chat/room)로 브로드캐스팅(url은 구독 url임)
        messagingTemplate.convertAndSend(ConstantUri.SUBSCRIBE_PREFIX + chatRoomId, messageDto);
    }

    //채팅방 및 채팅 참여자 생성
    @PostMapping("/api/v1/chatRoom/initiate")
    public ResponseEntity<Map<String,Long>> initiateChat(@RequestBody ChatInitiateDto requestDto) {
        //create ChatRoom, ChatRoomMember at once
        Long chatRoomId = chatService.initiateChat(requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap("chatRoomId", chatRoomId));
    }

}
