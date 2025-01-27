package com.hongik.mentor.hongik_mentor.service;

import com.hongik.mentor.hongik_mentor.controller.dto.chat.ChatMessageDto;
import com.hongik.mentor.hongik_mentor.controller.dto.chat.ChatRoomDto;
import com.hongik.mentor.hongik_mentor.controller.dto.chat.ChatRoomResponseDto;
import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.domain.chat.ChatMessage;
import com.hongik.mentor.hongik_mentor.domain.chat.ChatRoom;
import com.hongik.mentor.hongik_mentor.domain.chat.ChatRoomMember;
import com.hongik.mentor.hongik_mentor.repository.ChatMessageRepository;
import com.hongik.mentor.hongik_mentor.repository.ChatRoomRepository;
import com.hongik.mentor.hongik_mentor.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/*
* Service for ChatRoom, ChatMessage
* */
@RequiredArgsConstructor
@Transactional
@Service
public class ChatService {
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;
    //ChatMessage CRUD
    public Long saveChatMessage(ChatMessageDto messageDto) {
        ChatMessage message = chatMessageRepository.save(messageDto.toEntity());
        return message.getId();
    }

    //ChatRoom CRUD
    public Long saveChatRoom(ChatRoomDto chatRoomDto) {
        ChatRoom savedChatRoom = chatRoomRepository.save(chatRoomDto.toEntity());
        return savedChatRoom.getId();
    }

    public ChatRoomResponseDto findChatRoom(Long id) {
        ChatRoom findChatRoom = chatRoomRepository.findById(id).orElseThrow();

        return new ChatRoomResponseDto(findChatRoom);

    }

    //ChatRoomMeber CRUD
    public void saveChatRoomMembers(Long chatRoomId, Map<Long,String> chatMembers) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow();

        chatMembers.forEach((id, nickname)->{
            Member member = memberRepository.findById(id);
            ChatRoomMember chatRoomMember = new ChatRoomMember(member, chatRoom, nickname);
            chatRoom.addChatMember(chatRoomMember);
        });
        chatRoomRepository.save(chatRoom);//cascade로 ChatRoomMember persist
    }



}
