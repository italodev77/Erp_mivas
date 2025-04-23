package dev.enterprisemanager.mivas.exception;

import dev.enterprisemanager.mivas.modules.user.enums.ErrorCode;

import java.time.LocalDateTime;
import java.util.List;

public class ApiUserErrorResponse {
    private int status;
    private String error;
    private String code;
    private String message;
    private LocalDateTime timestamp;
    private List<String> errors;


    public ApiUserErrorResponse(int status, String error, ErrorCode errorCode) {
        this(status, error, errorCode, null);
    }

    public ApiUserErrorResponse(int status, String error, ErrorCode errorCode, List<String> errors) {
        this.status = status;
        this.error = error;
        this.code = errorCode.code();
        this.message = errorCode.message();
        this.timestamp = LocalDateTime.now();
        this.errors = errors;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public List<String> getErrors() {
        return errors;
    }
}
