package com.core.toy3.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.core.toy3.common.response.ResponseStatus.*;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "statusCode", "message", "data"})
public class Response<T> {

    private Boolean isSuccess;
    private Integer statusCode;
    private String message;
    private ResponseStatus status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    // 성공시 응답 모델
    public static <T> Response<T> response(T data) {
        return new Response<>(
                SUCCESS.isSuccess(),
                SUCCESS.getStatusCode(),
                SUCCESS.getMessage(),
                SUCCESS,
                data
        );
    }

    // 실패시 응답
    public static <T> Response<T> response(ResponseStatus status) {
        return new Response<>(
                status.isSuccess(),
                status.getStatusCode(),
                status.getMessage(),
                status,
                null
        );
    }
}
