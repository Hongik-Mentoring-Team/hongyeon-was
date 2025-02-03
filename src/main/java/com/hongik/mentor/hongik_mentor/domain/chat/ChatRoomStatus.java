package com.hongik.mentor.hongik_mentor.domain.chat;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ChatRoomStatus {
    OPEN("채팅가능"), CLOSE("채팅불가"), TEMP("임시 및 채팅불가");

    private final String title;
}
