package com.hongik.mentor.hongik_mentor.controller.dto;

import com.hongik.mentor.hongik_mentor.domain.*;
import com.hongik.mentor.hongik_mentor.domain.tier.Tier;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MemberResponseDto {

    private String socialId;    //socialId+provider를 조합하여 유저를 구분함
    private SocialProvider socialProvider;
    private String name;
    private String major;
    private Integer graduationYear;
    private MemberType type;    //재학생/졸업생
    private AccountStatus accountStatus;
    private Role role;
    private List<MemberBadgeResponseDto> badges = new ArrayList<>();
    private String mainBadgeUrl;
    private Tier tier;
    private Long rank_value;


    public MemberResponseDto(Member member) {
        this.socialId = member.getSocialId();
        this.socialProvider = member.getSocialProvider();
        this.name=member.getName();
        this.major = member.getMajor();
        this.graduationYear=member.getGraduationYear();
        this.type=member.getType();
        this.accountStatus=member.getAccountStatus();
        this.role = member.getRole();
        member.getBadges()
                .stream()
                .map(badge -> new MemberBadgeResponseDto(badge))
                .forEach(dto -> badges.add(dto));
        this.mainBadgeUrl = member.getMainBadgeUrl();
        this.tier = member.getTier();
        this.rank_value = member.getRank_value();

    }


}
