package com.hongik.mentor.hongik_mentor.controller.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
@Data
public class ChatInitiateDto {
    private String roomName;    //채팅방 이름
    private Map<Long, String> membersInfo; //memberId : nickname

}

//json 형식:
  /*
{
  "roomName": "Study Group",
  "membersInfo": {
    "1": "John",
    "2": "Jane"
  }
}

* */
