package com.practice.orderservice.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderErrorResponse {
    private String code;
    private String message;
}

