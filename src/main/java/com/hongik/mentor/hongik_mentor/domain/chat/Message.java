package com.hongik.mentor.hongik_mentor.domain.chat;

import com.hongik.mentor.hongik_mentor.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
@Entity
public class Message {
    @Id @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "member_id")
    private Member sender;
    private String content;

    public Message(ChatRoom chatRoom, String content, Member sender) {
        this.chatRoom = chatRoom;
        this.content = content;
        this.sender = sender;
    }
}
