package com.hongik.mentor.hongik_mentor.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MentoringExceptionHandler {

    @ExceptionHandler(CustomMentorException.class)
    public ResponseEntity<ErrorResponseEntity> handleCustomMentorException(CustomMentorException e) {
        return ErrorResponseEntity.errorResponseEntity(e.getErrorCode());
    }


}
