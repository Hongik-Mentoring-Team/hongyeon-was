package com.hongik.mentor.hongik_mentor.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCreateDto {

    @Schema(description = "댓글 내용", example = "예시 댓글")
    private String content;

    @Schema(description = "댓글 작성자 식별자", example = "1")
    private Long memberId;

    @Schema(description = "댓글이 추가될 게시글 식별자", example = "2")
    private Long postId;

    public CommentCreateDto(String content, Long memberId, Long postId) {
        this.content = content;
        this.memberId = memberId;
        this.postId = postId;
    }
}
