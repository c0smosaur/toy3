package com.core.toy3.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.OK;

@Getter
@AllArgsConstructor
public enum ResponseStatus {

    SUCCESS(true, OK.value(), "요청에 성공했습니다.");





    private final boolean isSuccess;
    private final int statusCode;
    private final String message;
}
