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

/*
* 기존 코드:
    @EntityGraph(attributePaths = {"chatMembers", "chatMessages"})  //성능최적화
    사용 이유: ChatRoom 조회시 ChatRoomMember, ChatMessage를 보통 같이 조회하므로. 하나의 쿼리로 처리가능
* 문제: 일대다(List)엔티티를 두개 이상 fetch join하면 예외 발생!
*
* => 변경
* @OrderColumn & @EntityGraph 같이 사용
* 또는
* @EntityGraph 제거
*
*
*
*
* */