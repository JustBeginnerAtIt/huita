package com.practice.orderservice.serviceImpl;

import com.practice.orderservice.dto.OrderRequestDto;
import com.practice.orderservice.dto.OrderResponseDto;
import com.practice.orderservice.mapping.OrderMapping;
import com.practice.orderservice.repository.OrderRepository;
import com.practice.orderservice.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapping orderMapping;

    @Override
    public void createOrder(OrderRequestDto orderRequestDto) {
        orderRepository.save(orderMapping.mapToEntity(orderRequestDto));
    }

    @Override
    public void deleteOrder(Integer orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public OrderResponseDto getOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapping::mapToDto)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapping::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDto getOrderByUserId(Integer userId) {
        return orderRepository.findByUserId(userId)
                .map(orderMapping::mapToDto)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
    }

    @Override
    public List<OrderResponseDto> getOrdersByUserId(Integer userId) {
        return orderRepository.findAllByUserId(userId)
                .stream()
                .map(orderMapping::mapToDto)
                .collect(Collectors.toList());
    }


}
