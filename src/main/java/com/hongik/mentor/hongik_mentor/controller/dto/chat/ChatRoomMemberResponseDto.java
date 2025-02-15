package com.hongik.mentor.hongik_mentor.controller.dto.chat;

import com.hongik.mentor.hongik_mentor.domain.chat.ChatRoomMember;
import lombok.Data;

@Data
public class ChatRoomMemberResponseDto {
    private Long id;
    private Long memberId;
    private Long chatRoomId;
    private String nickname;

    public ChatRoomMemberResponseDto(ChatRoomMember chatRoomMember) {
        this.id = chatRoomMember.getId();
        this.memberId = chatRoomMember.getMember().getId();
        this.chatRoomId = chatRoomMember.getChatRoom().getId();
        this.nickname = chatRoomMember.getNickname();
    }
}
