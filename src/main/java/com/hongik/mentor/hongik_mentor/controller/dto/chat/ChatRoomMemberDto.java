package com.hongik.mentor.hongik_mentor.controller.dto.chat;

import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.domain.chat.ChatRoom;
import com.hongik.mentor.hongik_mentor.domain.chat.ChatRoomMember;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ChatRoomMemberDto {
    private Long id;
    private Long memberId;
    private Long chatRoomId;
    private String nickname;

    public ChatRoomMemberDto(ChatRoomMember chatRoomMember) {
        this.id = chatRoomMember.getId();
        this.memberId = chatRoomMember.getMember().getId();
        this.chatRoomId = chatRoomMember.getChatRoom().getId();
        this.nickname = chatRoomMember.getNickname();
    }
}
