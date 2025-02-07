package com.hongik.mentor.hongik_mentor.controller.dto;


import com.hongik.mentor.hongik_mentor.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PostCreateDTO {

    private String title;

    private String content;

    private List<Long> tagId;

//    private Long memberId; => 게시글 생성시 접속 세션에서 Id조회

}
