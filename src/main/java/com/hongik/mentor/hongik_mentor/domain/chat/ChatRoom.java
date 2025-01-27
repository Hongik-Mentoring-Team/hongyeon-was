package com.hongik.mentor.hongik_mentor.domain.chat;

import com.hongik.mentor.hongik_mentor.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter @NoArgsConstructor
@Entity
public class ChatRoom {
    @Id @GeneratedValue
    @Column(name = "chatroom_id")
    private Long id;
    private String name;    //채팅방 이름
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true) //ChatRoomMember레포를 새로만들지 않고 cascade처리함
    private List<ChatRoomMember> chatMembers = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private ChatRoomStatus roomStatus;

    public ChatRoom(String name) {
        this.name = name;
        this.roomStatus = ChatRoomStatus.OPEN;
    }

    //양방향 연관관계 편의 메서드
    public void addChatMember(ChatRoomMember member) {
        chatMembers.add(member);
        member.setChatRoom(this);
    }

}
