package com.core.toy3.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@Getter
@AllArgsConstructor
public enum ResponseStatus {

    /**
     * 200 (OK)
     */
    SUCCESS(true, OK.value(), "요청에 성공했습니다."),

    /**
     * 400 (Bad_Request)
     */
    TRAVEL_DOES_NOT_EXIST(false, BAD_REQUEST.value(), "해당 여행 목록이 존재하지 않습니다."),
    TRIP_DOES_NOT_EXIST(false, BAD_REQUEST.value(), "해당 여정 목록이 존재하지 않습니다."),
    DELETE_IS_FAIL(false, BAD_REQUEST.value(), "삭제에 실패했습니다."),
    ARRIVALTIME_IS_INCORRECT(false, BAD_REQUEST.value() , "도착 시간이 잘못 설정되었습니다."),
    POSTAT_IS_INCORRECT(false, BAD_REQUEST.value(), "여정 시간이 잘못 설정되었습니다.");

    /**
     * 500(Invalid_Error)
     */





    private final boolean isSuccess;
    private final int statusCode;
    private final String message;
}
