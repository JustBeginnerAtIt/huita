package com.practice.orderservice.mapping;

import com.practice.orderservice.dto.OrderRequestDto;
import com.practice.orderservice.dto.OrderResponseDto;
import com.practice.orderservice.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapping {
    public OrderResponseDto mapToDto(Order order) {

        if (order == null) {
            return null;
        }

        return OrderResponseDto.builder()
                .orderId(order.getOrderId())
                .description(order.getDescription())
                .price(order.getPrice())
                .userId(order.getUserId())
                .build();
    }

    public Order mapToEntity(OrderRequestDto orderDto) {

        if (orderDto == null) {
            return null;
        }

        return Order.builder()
                .description(orderDto.getDescription())
                .price(orderDto.getPrice())
                .userId(orderDto.getUserId())
                .build();
    }
}
