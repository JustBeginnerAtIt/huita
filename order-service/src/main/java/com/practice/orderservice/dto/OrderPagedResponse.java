package com.practice.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;

public record OrderPagedResponse<T>(
        @JsonProperty("success") Boolean success,
        @JsonProperty("message") String message,
        @JsonProperty("data") List<T> data,
        @JsonProperty("page") int page,
        @JsonProperty("size") int size,
        @JsonProperty("totalElements") long totalElements,
        @JsonProperty("totalPages") int totalPages,
        @JsonProperty("timestamp") LocalDateTime timestamp
) {
    public static <T> OrderPagedResponse<T> of(List<T> data, int page, int size, long totalElements, int totalPages, String message) {
        return new OrderPagedResponse<>(true, message, data, page, size, totalElements, totalPages, LocalDateTime.now());
    }
}

