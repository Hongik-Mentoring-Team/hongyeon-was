package com.hongik.mentor.hongik_mentor.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public enum Role {
    ADMIN("ROLE_ADMIN", "관리자"), USER("ROLE_USER", "일반 사용자")
    ,TEMP("ROLE_TEMP", "회원가입 중인 사용자");

    private final String key;
    private final String title;
}
