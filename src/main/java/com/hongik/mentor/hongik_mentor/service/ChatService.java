package com.hongik.mentor.hongik_mentor.service;

import com.hongik.mentor.hongik_mentor.controller.dto.chat.*;
import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.domain.chat.ChatMessage;
import com.hongik.mentor.hongik_mentor.domain.chat.ChatRoom;
import com.hongik.mentor.hongik_mentor.domain.chat.ChatRoomMember;
import com.hongik.mentor.hongik_mentor.exception.ErrorCode;
import com.hongik.mentor.hongik_mentor.exception.InitiateChatException;
import com.hongik.mentor.hongik_mentor.repository.ChatRoomRepository;
import com.hongik.mentor.hongik_mentor.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/*
* Service for ChatRoom, ChatMessage
* */
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;
    //ChatMessage CRUD
    //등록
    @Transactional
    public Long saveChatMessage(Long chatRoomId, ChatMessageDto messageDto) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow();
        Member sender = memberRepository.findById(messageDto.getMemberId()).orElseThrow();
        ChatMessage entity = messageDto.toEntity(sender);

        chatRoom.addChatMessage(entity);
        chatRoomRepository.save(chatRoom);  //cascade로 chatMessage persist됨.
        return entity.getId();
    }
    //조회
    public List<ChatMessageResponseDto> findMessages(Long chatRoomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow();

        return chatRoom.getChatMessages()
                .stream()
                .map(chatMessage -> new ChatMessageResponseDto(chatMessage))
                .collect(Collectors.toList());
    }

    //ChatRoom CRUD
    //등록
    @Transactional
    public Long saveChatRoom(ChatRoomDto chatRoomDto) {
        ChatRoom savedChatRoom = chatRoomRepository.save(chatRoomDto.toEntity());
        return savedChatRoom.getId();
    }

    //조회
    public ChatRoomResponseDto findChatRoom(Long chatRoomId) {
        ChatRoom findChatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow();

        return new ChatRoomResponseDto(findChatRoom);

    }
    public List<ChatRoomResponseDto> findChatRoomByMemberId(Long memberId) {
        List<ChatRoom> chatRooms = chatRoomRepository.findByMemberId(memberId);

        return chatRooms
                .stream()
                .map(chatRoom -> new ChatRoomResponseDto(chatRoom))
                .collect(Collectors.toList());
    }
    //수정
    //삭제
    @Transactional
    public Long deleteChatRoom(Long chatRoomId) {
        chatRoomRepository.deleteById(chatRoomId);
        return chatRoomId;
    }

    //ChatRoomMember CRUD
    //등록
    @Transactional
    public void saveChatRoomMembers(Long chatRoomId, Map<Long,String> chatMembersInfo) {
        try {
            ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow();

            chatMembersInfo.forEach((id, nickname) -> {
                Member member = memberRepository.findById(id).orElseThrow();
                ChatRoomMember chatRoomMember = new ChatRoomMember(member, chatRoom, nickname);
                chatRoom.addChatMember(chatRoomMember);
            });
            chatRoomRepository.save(chatRoom);//cascade로 ChatRoomMember persist
        } catch (NoSuchElementException e) {
            throw new InitiateChatException(ErrorCode.INITIATE_CHAT_IMPOSSIBLE);
        }

    }

    //초기 채팅방 생성
    @Transactional
    public Long initiateChat(ChatInitiateDto requestDto) {
        String roomName = requestDto.getRoomName();
        Long roomId = this.saveChatRoom(new ChatRoomDto(roomName));
        this.saveChatRoomMembers(roomId, requestDto.getMembersInfo());

        return roomId;
    }

}
