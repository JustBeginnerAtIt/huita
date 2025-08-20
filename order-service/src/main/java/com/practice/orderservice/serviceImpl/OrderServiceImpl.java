package com.practice.orderservice.serviceImpl;

import com.practice.orderservice.dto.OrderPagedResponse;
import com.practice.orderservice.dto.OrderRequestDto;
import com.practice.orderservice.dto.OrderResponseDto;
import com.practice.orderservice.entity.Order;
import com.practice.orderservice.mapping.OrderMapping;
import com.practice.orderservice.repository.OrderRepository;
import com.practice.orderservice.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

//TODO: добавить логирование, а также продолжить с gateway service и разобраться почему discoveryservice не работает

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapping orderMapping;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        orderRepository.save(orderMapping.mapToEntity(orderRequestDto));
        return null;
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

    @Override
    public OrderPagedResponse<OrderResponseDto> getAllOrdersByPage(Integer page,  Integer size) {

        log.debug("Making page request to find all orders with page: {} and size: {}",  page, size);
        Pageable pageable =  PageRequest.of(page, size);
        Page<Order> orderPage = orderRepository.findAll(pageable);
        log.info("orders found with page {} and size {}", page, size);

        log.debug("Mapping page request to list of order DTO's");
        List<OrderResponseDto> orders = orderPage.getContent()
                .stream()
                .map(orderMapping::mapToDto)
                .toList();
        log.debug("Mapped list of orders: {}", orders);

        log.debug("Returning paged response of orders: {} with page number: {} and page size: {}", orders,  page, size);
        return OrderPagedResponse.of(
                orders,
                orderPage.getNumber(),
                orderPage.getSize(),
                orderPage.getTotalElements(),
                orderPage.getTotalPages(),
                "Orders retrieved successfully"
        );


    }


}
