package com.hongik.mentor.hongik_mentor.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class ErrorResponseEntity {

    private int status;

    private String message;

    private String detail;

    public static ResponseEntity<ErrorResponseEntity> errorResponseEntity(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ErrorResponseEntity.builder()
                        .status(errorCode.getHttpStatus().value())
                        .message(errorCode.getMessage())
                        .detail(errorCode.name()).build());
    }

}
