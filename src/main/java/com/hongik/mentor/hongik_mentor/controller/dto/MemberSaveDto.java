package com.hongik.mentor.hongik_mentor.controller.dto;

import com.hongik.mentor.hongik_mentor.domain.AccountStatus;
import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.domain.MemberType;
import com.hongik.mentor.hongik_mentor.domain.SocialProvider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public class MemberSaveDto {
    private final String socialId;    //socialId+provider를 조합하여 유저를 구분함
    private final SocialProvider socialProvider;
    private final String name;
    private final String major;
    private final Integer graduationYear;

    private AccountStatus accountStatus;


    public Member toEntity() {
        return new Member(socialId, socialProvider, name, major, graduationYear);
    }
}
