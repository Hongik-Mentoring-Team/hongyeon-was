package com.hongik.mentor.hongik_mentor.service.dto;

import com.hongik.mentor.hongik_mentor.domain.post.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentDto {

    private Long commentId;

    private String content;

    private String author;

    private LocalDateTime commentDate;

    @Builder
    public CommentDto(Long commentId, String content, String author, LocalDateTime commentDate) {
        this.commentId = commentId;
        this.content = content;
        this.author = author;
        this.commentDate = commentDate;
    }

    public static CommentDto fromEntity(String author,Comment comment) {
        return CommentDto.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .commentDate(comment.getCreatedAt())
                .author(author)
                .build();

    }
}
