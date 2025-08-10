package com.practice.orderservice.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderErrorResponse {
    private String code;
    private String message;
    private LocalDateTime timestamp;
}

