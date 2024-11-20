package com.hongik.mentor.hongik_mentor.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MemberType {
    student("재학생"), graduate("졸업생");

    private final String title;
}
