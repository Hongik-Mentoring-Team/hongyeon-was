package com.hongik.mentor.hongik_mentor.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class PostModifyDTO {


    private Long postId;

    private String title;

    private String content;

    private List<Long> tagIds = new ArrayList<>();

    private Long memberId;
}
