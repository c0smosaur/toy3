package com.core.toy3.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@Getter
@AllArgsConstructor
public enum ResponseStatus {

    SUCCESS(true, OK.value(), "요청에 성공했습니다."),

    DUPLICATE_USERNAME(false, BAD_REQUEST.value(),"이미 등록된 이메일입니다."),
    EMAIL_VALIDATION_ERROR(false, BAD_REQUEST.value(), "잘못된 형식의 이메일입니다.");


    private final boolean isSuccess;
    private final int statusCode;
    private final String message;
}
