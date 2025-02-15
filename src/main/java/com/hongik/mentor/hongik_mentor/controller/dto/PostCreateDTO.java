package com.hongik.mentor.hongik_mentor.controller.dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PostCreateDTO {

    private String title;

    private String content;

    private List<Long> tagId;

    private Long memberId;

    @Builder
    public PostCreateDTO(String title, String content, List<Long> tagId, Long memberId) {
        this.title = title;
        this.content = content;
        this.tagId = tagId;
        this.memberId = memberId;
    }


}
