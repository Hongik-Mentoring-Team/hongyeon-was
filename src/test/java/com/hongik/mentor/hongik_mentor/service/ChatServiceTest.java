package com.hongik.mentor.hongik_mentor.service;

import com.hongik.mentor.hongik_mentor.controller.dto.MemberSaveDto;
import com.hongik.mentor.hongik_mentor.controller.dto.chat.ChatMessageDto;
import com.hongik.mentor.hongik_mentor.controller.dto.chat.ChatRoomDto;
import com.hongik.mentor.hongik_mentor.controller.dto.chat.ChatRoomResponseDto;
import com.hongik.mentor.hongik_mentor.domain.SocialProvider;
import com.hongik.mentor.hongik_mentor.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
@Slf4j
@ActiveProfiles("test")
@Transactional
@SpringBootTest
class ChatServiceTest {
    @Autowired
    ChatService chatService;
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Test
    void 채팅방_생성_테스트() {
        Long member1Id = memberService.save(new MemberSaveDto("1", SocialProvider.GOOGLE, "olaf", "CS", 2025));
        Long member2Id = memberService.save(new MemberSaveDto("2", SocialProvider.GOOGLE, "tryn", "EC", 2025));

        //when
        //user input: 채팅방 참여자 목록
        Long roomId = chatService.saveChatRoom(new ChatRoomDto("roomA"));
        log.info("chatroom id={}", roomId);

        Map<Long, String> chatMembersInfo = new HashMap<>();
        chatMembersInfo.put(member1Id, "olafN");
        chatMembersInfo.put(member2Id, "trynN");

        chatService.saveChatRoomMembers(roomId, chatMembersInfo);

        ChatRoomResponseDto findRoom = chatService.findChatRoom(roomId);

        //then
        ChatRoomResponseDto chatRoom = chatService.findChatRoom(roomId);
        assertThat(chatRoom.getName()).isEqualTo("roomA");
        assertThat(chatRoom.getChatMembers()).hasSize(2);

    }

    @Test
    void 메시지_저장_테스트() {

    }
}