package com.hongik.mentor.hongik_mentor.controller.dto;

import com.hongik.mentor.hongik_mentor.domain.Category;
import com.hongik.mentor.hongik_mentor.domain.chat.ChatRoomType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class PostModifyDTO {

    private String title;

    private String content;

    private List<Long> tagIds;

    private int capacity; //모집인원

}
