package com.hongik.mentor.hongik_mentor.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponseEntity<T> {

    @Schema(description = "응답 코드", example = "201")
    private int code;

    @Schema(description = "HTTP 상태", example = "OK")
    private HttpStatus httpStatus;

    @Schema(description = "응답 메세지", example = "성공 응답 메세지 예시")
    private String message;

    @Schema(description = "응답 데이터")
    private T data;

    public ApiResponseEntity(HttpStatus httpStatus, String message, T data) {
        this.code = httpStatus.value();
        this.httpStatus = httpStatus;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponseEntity<T> of(HttpStatus httpStatus, String message, T data) {
        return new ApiResponseEntity<>(httpStatus, message, data);
    }


}
