package com.hongik.mentor.hongik_mentor.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MemberType {
    STUDENT("재학생"), GRADUATE("졸업생");

    private final String title;
}
