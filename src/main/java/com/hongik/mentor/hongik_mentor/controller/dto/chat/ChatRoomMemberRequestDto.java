package com.hongik.mentor.hongik_mentor.controller.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ChatRoomMemberRequestDto {
    private Long memberId;
    private Long chatRoomId;
    private String nickname;

}
