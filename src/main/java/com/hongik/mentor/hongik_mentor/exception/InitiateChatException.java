package com.hongik.mentor.hongik_mentor.exception;

import lombok.Getter;

@Getter
public class InitiateChatException extends RuntimeException{
    private ErrorCode errorCode;

    public InitiateChatException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
