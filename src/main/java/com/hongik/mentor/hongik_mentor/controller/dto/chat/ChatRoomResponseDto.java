package com.hongik.mentor.hongik_mentor.controller.dto.chat;

import com.hongik.mentor.hongik_mentor.domain.chat.ChatRoom;
import com.hongik.mentor.hongik_mentor.domain.chat.ChatRoomMember;
import com.hongik.mentor.hongik_mentor.domain.chat.ChatRoomStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ChatRoomResponseDto {
        private Long id;
        private String name;    //채팅방 이름
        private List<ChatRoomMemberDto> chatMembers = new ArrayList<>();
        private ChatRoomStatus roomStatus;

        public ChatRoomResponseDto(ChatRoom chatRoom) {
                this.id = chatRoom.getId();
                this.name = chatRoom.getName();
                this.roomStatus = chatRoom.getRoomStatus();
                this.chatMembers = chatRoom.getChatMembers()
                        .stream()
                        .map(chatRoomMember -> new ChatRoomMemberDto(chatRoomMember))
                        .collect(Collectors.toList());

        }
}
