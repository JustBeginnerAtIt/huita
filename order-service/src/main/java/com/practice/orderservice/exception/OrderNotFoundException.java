package com.practice.orderservice.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Integer orderId) {
        super("Order with ID " + orderId + " not found");
    }

    public OrderNotFoundException(String message) {
        super(message);
    }
}
