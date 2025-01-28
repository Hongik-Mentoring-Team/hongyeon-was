package com.hongik.mentor.hongik_mentor.controller.dto;

import com.hongik.mentor.hongik_mentor.domain.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagDTO {

    private Long tagId;

    private String tagName;

    public static TagDTO fromTag(Tag tag) {
        return TagDTO.builder()
                .tagId(tag.getId())
                .tagName(tag.getName())
                .build();
    }
}
