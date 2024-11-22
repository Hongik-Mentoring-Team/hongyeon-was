package com.hongik.mentor.hongik_mentor.oauth.dto;

import com.hongik.mentor.hongik_mentor.domain.Member;
import com.hongik.mentor.hongik_mentor.domain.MemberType;
import lombok.Getter;

@Getter
public class SessionMember {
    private String name;
    private String major;
    private Integer graduationYear;
    private MemberType type;

    public SessionMember(Member member) {
        this.name = member.getName();
        this.major=member.getMajor();
        this.graduationYear= member.getGraduationYear();
        this.type = member.getType();
    }
}
