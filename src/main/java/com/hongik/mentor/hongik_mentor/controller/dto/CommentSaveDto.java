package com.hongik.mentor.hongik_mentor.controller.dto;

import com.hongik.mentor.hongik_mentor.domain.Comment;
import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.domain.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentSaveDto {
    private String content;
    private Long commenterId;
    private Long postId;

    public Comment toEntity(Member commenter, Post post) {
        Comment comment = new Comment();
        comment.setContent(this.content);
        comment.setCommenter(commenter);
        comment.setPost(post);
        return comment;
    }
}
