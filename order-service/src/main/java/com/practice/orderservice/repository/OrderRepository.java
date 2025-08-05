package com.practice.orderservice.repository;

import com.practice.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o WHERE o.userId = :userId")
    Optional<Order> findByUserId(Integer userId);
    List<Order> findAllByUserId(Integer userId);
}
