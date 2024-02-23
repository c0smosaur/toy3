package com.core.toy3.common.exception;

import com.core.toy3.common.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Response> CustomExceptionHandler(CustomException e) {
        log.warn("[CustomException Occurs] message: {} HttpStatusCode: {}", e.getStatus().getMessage(),
                e.getStatus().getStatusCode());

        return ResponseEntity.status(e.getStatus().getStatusCode())
                .body(Response.response(e.getStatus()));
    }
}
