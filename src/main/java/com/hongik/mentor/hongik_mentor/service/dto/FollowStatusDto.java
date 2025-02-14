package com.hongik.mentor.hongik_mentor.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FollowStatusDto {

    private Long memberId;

    private int followers;

    private int followings;

    @Builder
    public FollowStatusDto(Long memberId, int followers, int followings) {
        this.memberId = memberId;
        this.followers = followers;
        this.followings = followings;
    }
}
