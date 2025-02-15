package com.hongik.mentor.hongik_mentor.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SearchByTagDto {

    private List<Long> tagIds;

}
