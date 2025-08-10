package com.practice.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class OrderServiceGlobalExceptionHandler {
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<OrderErrorResponse> handleUserNotFoundException(OrderNotFoundException ex) {
        return new ResponseEntity<>(
                new OrderErrorResponse(
                        ErrorCode.NOT_FOUND.getDescription(),
                        ex.getMessage(),
                        LocalDateTime.now()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<OrderErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(
                new OrderErrorResponse(
                        ErrorCode.BAD_REQUEST.getDescription(),
                        ex.getMessage(),
                        LocalDateTime.now()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<OrderErrorResponse> handleException(Exception ex) {
        return new ResponseEntity<>(
                new OrderErrorResponse(
                        ErrorCode.INTERNAL_SERVER_ERROR.getDescription(),
                        ex.getMessage(),
                        LocalDateTime.now()
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<OrderErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return new ResponseEntity<>(
                new OrderErrorResponse(
                        ErrorCode.VALIDATION_ERROR.getDescription(),
                        errors,
                        LocalDateTime.now()
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}
