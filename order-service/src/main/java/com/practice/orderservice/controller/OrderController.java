package com.practice.orderservice.controller;

import com.practice.orderservice.dto.OrderRequestDto;
import com.practice.orderservice.dto.OrderResponseDto;
import com.practice.orderservice.exception.OrderApiResponse;
import com.practice.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderApiResponse<OrderResponseDto>> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto createdOrder = orderService.createOrder(orderRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(OrderApiResponse.getSuccessResponse("Order created successfully", createdOrder));
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<OrderApiResponse<Void>> deleteOrder(@PathVariable("orderId") Integer orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok(OrderApiResponse.getSuccessResponse("Order deleted successfully", null));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderApiResponse<OrderResponseDto>> getOrder(@PathVariable("orderId") Integer orderId) {
        OrderResponseDto gotOrder = orderService.getOrderById(orderId);
        return ResponseEntity.ok(OrderApiResponse.getSuccessResponse("Order found successfully", gotOrder));
    }

    @GetMapping("/all")
    public ResponseEntity<OrderApiResponse<List<OrderResponseDto>>> getOrders() {
        List<OrderResponseDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(OrderApiResponse.getSuccessResponse("Orders found successfully", orders));
    }

    @GetMapping("/user/{userId}/orders")
    public ResponseEntity<OrderApiResponse<List<OrderResponseDto>>> getOrdersByUserId(@Valid @PathVariable("userId") Integer userId) {
        List<OrderResponseDto> ordersByUserId = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(OrderApiResponse.getSuccessResponse("Orders found successfully by User ID", ordersByUserId));
    }

    @GetMapping("/user/{userId/order}")
    public ResponseEntity<OrderApiResponse<OrderResponseDto>> getOrderByUserId(@Valid @PathVariable("userId") Integer userId) {
        OrderResponseDto orderByUserId = orderService.getOrderByUserId(userId);
        return ResponseEntity.ok(OrderApiResponse.getSuccessResponse("Order found successfully by User ID", orderByUserId));
    }
}
