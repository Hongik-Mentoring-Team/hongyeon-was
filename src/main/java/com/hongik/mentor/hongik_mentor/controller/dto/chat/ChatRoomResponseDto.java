package com.hongik.mentor.hongik_mentor.controller.dto.chat;

import com.hongik.mentor.hongik_mentor.domain.chat.ChatRoom;
import com.hongik.mentor.hongik_mentor.domain.chat.ChatRoomStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ChatRoomResponseDto {
        private Long id;
        private String name;    //채팅방 이름
        private List<ChatRoomMemberResponseDto> chatMembers = new ArrayList<>();
        private List<ChatMessageResponseDto> chatMessages = new ArrayList<>();
        private ChatRoomStatus roomStatus;

        public ChatRoomResponseDto(ChatRoom chatRoom) {
                this.id = chatRoom.getId();
                this.name = chatRoom.getName();
                this.roomStatus = chatRoom.getRoomStatus();
                this.chatMembers = chatRoom.getChatMembers()
                        .stream()
                        .map(chatRoomMember -> new ChatRoomMemberResponseDto(chatRoomMember))
                        .collect(Collectors.toList());
                this.chatMessages = chatRoom.getChatMessages()
                        .stream()
                        .map(chatMessage -> new ChatMessageResponseDto(chatMessage))
                        .collect(Collectors.toList());

        }
}
