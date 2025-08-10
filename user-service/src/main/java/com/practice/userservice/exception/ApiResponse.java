package com.practice.userservice.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
public record ApiResponse<T>(
        @JsonProperty("success") Boolean success,
        @JsonProperty("message") String message,
        @JsonProperty("data") Optional<T> data,
        @JsonProperty("timestamp") LocalDateTime timestamp
) {

    public static <T> ApiResponse<T> getSuccessResponse(String message, T data) {
        log.info("Success response: {}", message);
        return new ApiResponse<>(true, message, Optional.ofNullable(data), LocalDateTime.now());
    }

    public static <T> ApiResponse<T> getFailureResponse(String message) {
        log.info("Failure response: {}", message);
        return new ApiResponse<>(false, message, Optional.empty(), LocalDateTime.now());
    }
}
