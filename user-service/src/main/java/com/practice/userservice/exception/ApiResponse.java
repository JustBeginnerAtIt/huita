package com.practice.userservice.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Optional;

public record ApiResponse<T>(
        @JsonProperty("success") Boolean success,
        @JsonProperty("message") String message,
        @JsonProperty("data") Optional<T> data,
        @JsonProperty("timestamp") LocalDateTime timestamp
) {

    private static final Logger logger = LoggerFactory.getLogger(ApiResponse.class);
    public static <T> ApiResponse<T> getSuccessResponse(String message, T data) {
        logger.info("Success response: {}", message);
        return new ApiResponse<T>(true, message, Optional.ofNullable(data), LocalDateTime.now());
    }

    public static <T> ApiResponse<T> getFailureResponse(String message) {
        logger.info("Failure response: {}", message);
        return new ApiResponse<T>(false, message, Optional.empty(), LocalDateTime.now());
    }
}
