package com.hongik.mentor.hongik_mentor.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    POST_NOT_EXISTS(HttpStatus.NOT_FOUND, "해당하는 게시글이 존재하지 않습니다");


    private final HttpStatus httpStatus;

    private final String message;
}
