package com.hongik.mentor.hongik_mentor.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterMemberException extends RuntimeException {
    private final ErrorCode errorCode;
}
