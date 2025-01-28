package com.hongik.mentor.hongik_mentor.controller;

import com.hongik.mentor.hongik_mentor.controller.dto.chat.ChatMessageDto;
import com.hongik.mentor.hongik_mentor.domain.chat.ChatMessage;
import com.hongik.mentor.hongik_mentor.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
@RequiredArgsConstructor
@Controller
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/message")//클라이언트가 /app/chat/message로 메시지 보냈을 때
    public void broadCastMessage(@Payload ChatMessageDto messageDto) {
        Long chatRoomId = chatService.saveChatMessage(messageDto);

        //해당 채팅방으로 메시지 전송
        //메시지를 /topic/chat/room로 브로드캐스팅
        messagingTemplate.convertAndSend("/topic/chat/" + chatRoomId, messageDto);
    }
}
