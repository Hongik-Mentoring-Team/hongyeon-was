package com.hongik.mentor.hongik_mentor.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class CreatedCommentDto {

    @Schema(description = "생성된 댓글 식별자", example = "1")
    private Long commentId;

    public CreatedCommentDto(Long commentId) {
        this.commentId = commentId;
    }
}
