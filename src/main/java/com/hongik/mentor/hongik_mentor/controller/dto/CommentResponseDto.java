package com.hongik.mentor.hongik_mentor.controller.dto;

import com.hongik.mentor.hongik_mentor.domain.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private Long id;
    private String content;
    private String commenterName;

    // 생성자: Comment 엔티티를 기반으로 DTO 생성
    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.commenterName = comment.getCommenter().getName(); // 댓글 작성자의 이름
    }
}