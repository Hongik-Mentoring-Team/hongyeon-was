package com.hongik.mentor.hongik_mentor.controller.dto;

import com.hongik.mentor.hongik_mentor.domain.HashTag;
import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveDto {

    private String title;
    private String content;
    private Long memberId;
    private HashTag hashTag;

    public Post toEntity(Member member) {
        Post post = new Post();
        post.setTitle(this.title);
        post.setContent(this.content);
        post.setHashTag(this.hashTag);
        post.setPoster(member);
        return post;
    }
}
