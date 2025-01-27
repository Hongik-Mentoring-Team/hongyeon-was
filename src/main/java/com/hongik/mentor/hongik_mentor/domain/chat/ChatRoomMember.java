package com.hongik.mentor.hongik_mentor.domain.chat;

import com.hongik.mentor.hongik_mentor.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
@Entity
public class ChatRoomMember {
    @Id @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;
    private String nickname;

    public ChatRoomMember(Member member, ChatRoom chatRoom, String nickname) {
        this.member = member;
        this.nickname = nickname;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }
}
