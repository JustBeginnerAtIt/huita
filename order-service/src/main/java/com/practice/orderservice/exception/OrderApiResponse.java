package com.practice.orderservice.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
public record OrderApiResponse<T> (
        @JsonProperty("success") Boolean success,
        @JsonProperty("message") String message,
        @JsonProperty("data") Optional<T> data,
        @JsonProperty("timestamp") LocalDateTime timestamp
) {

    public static <T> OrderApiResponse<T> getSuccessResponse(String message, T data) {
        log.info("Success response: {}", message);
        return new OrderApiResponse<>(true, message, Optional.ofNullable(data), LocalDateTime.now());
    }

    public static <T> OrderApiResponse<T> getFailureResponse(String message) {
        log.info("Failure response: {}", message);
        return new OrderApiResponse<>(false, message, Optional.empty(), LocalDateTime.now());
    }
}
