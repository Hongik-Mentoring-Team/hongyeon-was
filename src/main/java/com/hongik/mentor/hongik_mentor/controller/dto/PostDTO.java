package com.hongik.mentor.hongik_mentor.controller.dto;

import com.hongik.mentor.hongik_mentor.domain.post.Post;
import com.hongik.mentor.hongik_mentor.service.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Long postId;

    private String title;

    private String content;

    private LocalDateTime createAt;

    private List<TagDTO> tags;

    private List<CommentDto> comments;

    public static PostDTO fromPost(Post post) {
        return PostDTO.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createAt(post.getCreatedAt())
                .tags(post.getTags().stream()
                        .map(postTag -> TagDTO.fromTag(postTag.getTag())).toList())
                .comments(post.getComments().stream()
                        .map(c -> CommentDto.fromEntity(post.getMember().getName(),c)).toList())
                .build();


    }

}
