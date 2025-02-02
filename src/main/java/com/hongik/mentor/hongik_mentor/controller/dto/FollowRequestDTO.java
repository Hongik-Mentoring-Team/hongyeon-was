package com.hongik.mentor.hongik_mentor.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FollowRequestDTO {

    private Long followerId;

    private Long followeeId;

    @Builder
    public FollowRequestDTO(Long followerId, Long followeeId) {
        this.followerId = followerId;
        this.followeeId = followeeId;
    }
}
