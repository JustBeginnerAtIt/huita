package com.practice.userservice.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    NOT_FOUND("Resource not found"),
    VALIDATION_ERROR("Validation error occurred"),
    INTERNAL_SERVER_ERROR("An internal server error occurred"),
    BAD_REQUEST("Bad request");

    private final String description;

    ErrorCode(String description) {
        this.description = description;
    }

}
