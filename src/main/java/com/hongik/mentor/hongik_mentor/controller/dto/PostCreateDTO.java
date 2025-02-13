package com.hongik.mentor.hongik_mentor.controller.dto;


import com.hongik.mentor.hongik_mentor.domain.Category;
import com.hongik.mentor.hongik_mentor.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PostCreateDTO {

    private String title;

    private String content;

    private Category category;

    private List<Long> tagId;

//    private Long memberId; => 게시글 생성시 접속 세션에서 Id조회

    @Builder
    public PostCreateDTO(String title, String content, List<Long> tagId, Category category) {
        this.title = title;
        this.content = content;
        this.tagId = tagId;
        this.category = category;
//        this.memberId=this.memberId; 게시글 생성시 접속 세션에서 Id조회
    }


}
