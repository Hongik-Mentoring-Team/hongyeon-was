package com.hongik.mentor.hongik_mentor.controller.dto;

import com.hongik.mentor.hongik_mentor.domain.Category;
import com.hongik.mentor.hongik_mentor.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    private Category category;

    public static PostDTO fromPost(Post post) {
        return PostDTO.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .createAt(post.getCreatedAt())
                .tags(post.getTags().stream()
                        .map(postTag -> TagDTO.fromTag(postTag.getTag())).toList())
                .category(post.getCategory())
                .build();


    }

}
