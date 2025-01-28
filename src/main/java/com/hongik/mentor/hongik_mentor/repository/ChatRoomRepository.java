package com.hongik.mentor.hongik_mentor.repository;

import com.hongik.mentor.hongik_mentor.domain.chat.ChatRoom;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    @EntityGraph(attributePaths = {"chatMembers", "chatMessages"})  //성능최적화
    @Query("select c.chatRoom from ChatRoomMember c where c.member.id = :memberId")
    List<ChatRoom> findByMemberId(Long memberId);
}
