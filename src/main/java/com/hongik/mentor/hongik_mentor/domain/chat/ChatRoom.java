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
    @OneToMany(mappedBy = "chatRoom"
            ,fetch = FetchType.EAGER
            , cascade = CascadeType.ALL, orphanRemoval = true) //ChatRoomMember 레포를 새로만들지 않고 cascade처리함
    private List<ChatRoomMember> chatMembers = new ArrayList<>();
    @OneToMany(mappedBy = "chatRoom"
            , fetch = FetchType.EAGER   //ChatMessage는 거의 항상 같이 조회하므로 Eager로딩
            , cascade = CascadeType.ALL, orphanRemoval = true) //ChatMessage 영속성 관리는 ChatRoom이 함
    private List<ChatMessage> chatMessages = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private ChatRoomStatus roomStatus;
    private String name;    //채팅방 이름

    public ChatRoom(String name) {
        this.name = name;
        this.roomStatus = ChatRoomStatus.OPEN;
    }

    //양방향 연관관계 편의 메서드
    public void addChatMember(ChatRoomMember member) {
        chatMembers.add(member);
        member.setChatRoom(this);
    }
    public void addChatMessage(ChatMessage message) {
        chatMessages.add(message);
        message.setChatRoom(this);
    }

}
