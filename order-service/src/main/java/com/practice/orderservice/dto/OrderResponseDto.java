package com.practice.orderservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponseDto {
    private Integer orderId;
    private String description;
    private Double price;
    private Integer userId;
}
