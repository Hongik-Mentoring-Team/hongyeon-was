package com.hongik.mentor.hongik_mentor.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomMentorException extends RuntimeException {

    private ErrorCode errorCode;
}
