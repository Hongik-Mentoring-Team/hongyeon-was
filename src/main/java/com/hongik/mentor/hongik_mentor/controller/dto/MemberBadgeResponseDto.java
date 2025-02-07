package com.hongik.mentor.hongik_mentor.controller.dto;

import com.hongik.mentor.hongik_mentor.domain.Badge;
import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.domain.MemberBadge;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
public class MemberBadgeResponseDto {
    private Long memberId;
    private String badgeName;
    private String badgeImageUrl;

    public MemberBadgeResponseDto(MemberBadge  memberBadge) {
        this.memberId = memberBadge.getId();
        badgeName = memberBadge.getBadge().getName();
        badgeImageUrl = memberBadge.getBadge().getName();
    }
}
