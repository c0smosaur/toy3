package com.core.toy3.common.exception;

import com.core.toy3.common.response.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {

    private final ResponseStatus status;
    private final String message;

    public CustomException(ResponseStatus status) {
        this.status = status;
        this.message = "";
    }

    public String getMessage() {
        if(message.isEmpty()) {
            return status.getMessage();
        }
        return String.format("%s %n[detail] %n%s", status.getMessage(), message);
    }
}
