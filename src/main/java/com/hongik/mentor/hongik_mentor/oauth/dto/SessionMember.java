package com.hongik.mentor.hongik_mentor.oauth.dto;

import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.domain.MemberType;
import com.hongik.mentor.hongik_mentor.domain.SocialProvider;
import lombok.Getter;

@Getter
public class SessionMember {
    private String socialId;
    private SocialProvider provider;
    private String name;
    private String major;
    private Integer graduationYear;
    private MemberType type;

    public SessionMember(Member member) {
        this.socialId=member.getSocialId();
        this.provider=member.getSocialProvider();

    }

    public void update(String name, String major, Integer graduationYear) {
        this.name=name;
        this.major=major;
        this.graduationYear=graduationYear;
    }
}
