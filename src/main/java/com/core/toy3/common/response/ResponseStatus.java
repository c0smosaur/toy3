package com.core.toy3.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
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
    COMMENT_NOT_FOUND(false, BAD_REQUEST.value(), "해당 정보를 가진 댓글이 없습니다"),
    USER_DOES_NOT_EXIST(false, BAD_REQUEST.value(), "사용자 정보가 존재하지 않습니다."),
    TRAVEL_DOES_NOT_EXIST(false, BAD_REQUEST.value(), "해당 여행 목록이 존재하지 않습니다."),
    TRIP_DOES_NOT_EXIST(false, BAD_REQUEST.value(), "해당 여정 목록이 존재하지 않습니다."),
    DELETE_IS_FAIL(false, BAD_REQUEST.value(), "삭제에 실패했습니다."),
    ARRIVALTIME_IS_INCORRECT(false, BAD_REQUEST.value() , "도착 시간이 잘못 설정되었습니다."),

    WRONG_ADDRESS(false, BAD_REQUEST.value(), "검색된 주소 결과가 없습니다."),
    USERINFO_IS_NOTFOUND(false, BAD_REQUEST.value(), "유저정보를 찾을 수 없습니다."),

    POSTAT_IS_INCORRECT(false, BAD_REQUEST.value(), "여정 시간이 잘못 설정되었습니다."),
    DUPLICATE_USERNAME(false, BAD_REQUEST.value(),"이미 등록된 이메일입니다."),
    EMAIL_VALIDATION_ERROR(false, BAD_REQUEST.value(), "잘못된 형식의 이메일입니다."),
    HAS_NOT_PERMISSION_TO_ACCESS(false, BAD_REQUEST.value(), "허가할 수 없는 접근입니다."),

    /**
     * 500(Invalid_Error)
     */
    INTERNAL_SERVER_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "예상치 못한 오류가 발생했습니다.");

  
    private final boolean isSuccess;
    private final int statusCode;
    private final String message;
}
