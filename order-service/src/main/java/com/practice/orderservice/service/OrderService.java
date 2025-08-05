package com.practice.orderservice.service;

import com.practice.orderservice.dto.OrderRequestDto;
import com.practice.orderservice.dto.OrderResponseDto;

import java.util.List;

public interface OrderService {
    void createOrder(OrderRequestDto orderDto);
    void deleteOrder(Integer orderId);
    OrderResponseDto getOrderById(Integer orderId);
    List<OrderResponseDto> getAllOrders();
    OrderResponseDto getOrderByUserId(Integer userId);
    List<OrderResponseDto> getOrdersByUserId(Integer userId);
}
