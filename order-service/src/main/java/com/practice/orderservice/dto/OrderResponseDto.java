package com.practice.orderservice.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class OrderResponseDto implements Serializable {
    private Integer orderId;
    private String description;
    private Double price;
    private Integer userId;
}
