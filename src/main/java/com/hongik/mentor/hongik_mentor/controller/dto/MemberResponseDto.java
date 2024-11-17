package com.hongik.mentor.hongik_mentor.controller.dto;

import com.hongik.mentor.hongik_mentor.domain.AccountStatus;
import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.domain.MemberType;
import com.hongik.mentor.hongik_mentor.domain.SocialProvider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class MemberResponseDto {

    private Long id;
    private String socialId;    //socialId+provider를 조합하여 유저를 구분함
    private SocialProvider socialProvider;
    private String name;
    private String major;
    private Integer graduationYear;
    private MemberType type;    //재학생/졸업생

    private AccountStatus accountStatus;

    public MemberResponseDto(Member member) {
        this.id=member.getId();
        this.socialId = member.getSocialId();
        this.socialProvider = member.getSocialProvider();
        this.name=member.getName();
        this.major = member.getMajor();
        this.graduationYear=member.getGraduationYear();
        this.type=member.getType();
        this.accountStatus=member.getAccountStatus();

    }


    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus=accountStatus;
    }
}
