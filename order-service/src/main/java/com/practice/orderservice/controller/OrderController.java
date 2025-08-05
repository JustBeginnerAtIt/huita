package com.practice.orderservice.controller;

import com.practice.orderservice.dto.OrderRequestDto;
import com.practice.orderservice.dto.OrderResponseDto;
import com.practice.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public void createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        orderService.createOrder(orderRequestDto);
    }

    @DeleteMapping("/delete/{orderId}")
    public void deleteOrder(@PathVariable("orderId") Integer orderId) {
        orderService.deleteOrder(orderId);
    }

    @GetMapping("/{orderId}")
    public OrderResponseDto getOrder(@PathVariable("orderId") Integer orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/all")
    public List<OrderResponseDto> getOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/user/{userId}/orders")
    public List<OrderResponseDto> getOrdersByUserId(@Valid @PathVariable("userId") Integer userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @GetMapping("/user/{userId/order}")
    public OrderResponseDto getOrderByUserId(@Valid @PathVariable("userId") Integer userId) {
        return orderService.getOrderByUserId(userId);
    }
}
