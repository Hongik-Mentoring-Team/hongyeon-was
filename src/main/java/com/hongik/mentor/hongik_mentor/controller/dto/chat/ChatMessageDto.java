package com.hongik.mentor.hongik_mentor.controller.dto.chat;

import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.domain.chat.ChatMessage;
import com.hongik.mentor.hongik_mentor.domain.chat.ChatRoom;

import java.time.LocalDateTime;

public class ChatMessageDto {
    private ChatRoom chatRoom;
    private Member sender;
    private String content;
    private LocalDateTime createdAt;

    public ChatMessageDto(ChatRoom chatRoom, Member sender, String content) {
        this.chatRoom = chatRoom;
        this.sender = sender;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    public ChatMessage toEntity() {
        return new ChatMessage(chatRoom, content, sender, createdAt);
    }
}
