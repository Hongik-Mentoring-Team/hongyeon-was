package com.hongik.mentor.hongik_mentor.controller.dto;

import com.hongik.mentor.hongik_mentor.domain.HashTag;
import com.hongik.mentor.hongik_mentor.domain.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private String posterName;
    private HashTag hashTag;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private List<CommentResponseDto> comments;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.posterName = post.getPoster().getName(); // 작성자의 이름
        this.hashTag = post.getHashTag();
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getModifidedDate();
        this.comments = post.getComments().stream()
                .map(CommentResponseDto::new) // Comment를 CommentResponseDto로 변환
                .collect(Collectors.toList());
    }
}
