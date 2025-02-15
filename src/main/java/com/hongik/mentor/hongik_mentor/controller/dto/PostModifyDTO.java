package com.hongik.mentor.hongik_mentor.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PostModifyDTO {


    private String title;

    private String content;

    private List<Long> tagIds = new ArrayList<>();

    private Long memberId;

    public PostModifyDTO(String title, String content, List<Long> tagIds, Long memberId) {
        this.title = title;
        this.content = content;
        this.tagIds = tagIds;
        this.memberId = memberId;
    }
}
