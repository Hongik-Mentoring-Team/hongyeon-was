package com.hongik.mentor.hongik_mentor.domain.chat;

import com.hongik.mentor.hongik_mentor.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter @NoArgsConstructor
@Entity
public class ChatMessage {
    @Id @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "member_id")
    private Member sender;
    private String content;
    private LocalDateTime createdAt;

    public ChatMessage(ChatRoom chatRoom, String content, Member sender, LocalDateTime createdAt) {
        this.chatRoom = chatRoom;
        this.content = content;
        this.sender = sender;
        this.createdAt = createdAt;
    }

    @PrePersist //em.flush직전 콜백
    public void prePersist() {
        createdAt = (createdAt == null ? LocalDateTime.now() : createdAt);
    }
}
